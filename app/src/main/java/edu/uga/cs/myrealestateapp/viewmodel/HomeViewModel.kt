package edu.uga.cs.myrealestateapp.viewmodel

import androidx.lifecycle.ViewModel
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.repository.PropertyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: PropertyRepository) : ViewModel() {
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
        // Implementation...
    }
}
