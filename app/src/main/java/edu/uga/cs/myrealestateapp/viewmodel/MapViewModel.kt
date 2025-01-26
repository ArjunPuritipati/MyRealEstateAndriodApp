package edu.uga.cs.myrealestateapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.repository.PropertyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MapViewModel(private val repository: PropertyRepository) : ViewModel() {

    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties

    fun fetchProperties(
        postalCode: String,
        radius: Int,
        minBeds: Int,
        maxBeds: Int,
        minBaths: Int,
        maxBaths: Int,
        propertyType: String
    ) {
        viewModelScope.launch {
            val result = repository.getProperties(
                postalCode, radius, minBeds, maxBeds, minBaths, maxBaths, propertyType
            )
            result.onSuccess {
                _properties.value = it
            }.onFailure {
                _properties.value = emptyList() // Handle the failure case
            }
        }
    }
}
