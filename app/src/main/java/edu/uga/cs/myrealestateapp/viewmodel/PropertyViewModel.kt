package edu.uga.cs.myrealestateapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.repository.PropertyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PropertyViewModel(private val repository: PropertyRepository) : ViewModel() {

    // State flows for properties and errors
    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Hold unfiltered properties for filtering logic
    private var allProperties: List<Property> = emptyList()

    // Fetch properties from the repository
    fun fetchProperties(
        postalCode: String,
        radius: Int,
        minBeds: Int,
        maxBeds: Int,
        minBaths: Int,
        maxBaths: Int,
        propertyType: String? = null // Optional filter
    ) {
        viewModelScope.launch {
            _error.value = null // Clear previous errors
            val result = repository.getProperties(
                postalCode = postalCode,
                radius = radius,
                minBeds = minBeds,
                maxBeds = maxBeds,
                minBaths = minBaths,
                maxBaths = maxBaths,
                propertyType = propertyType ?: ""
            )
            result.onSuccess { fetchedProperties ->
                allProperties = fetchedProperties // Cache all properties
                _properties.value = fetchedProperties
            }.onFailure { throwable ->
                _error.value = throwable.message
            }
        }
    }

    // Filter properties by property type
    fun filterPropertiesByType(type: String) {
        _properties.value = if (type == "All") {
            allProperties
        } else {
            allProperties.filter { it.summary?.propertyType == type }
        }
    }

    // Fetch property details by ID
    fun fetchPropertyDetails(propertyId: Long) {
        viewModelScope.launch {
            _error.value = null // Clear previous errors
            val result = repository.getPropertyDetails(propertyId)
            result.onFailure { throwable ->
                _error.value = throwable.message
            }
            // You can manage fetched details here if needed
        }
    }
}
