package edu.uga.cs.myrealestateapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.repository.PropertyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: PropertyRepository) : ViewModel() {

    private val _selectedProperty = MutableStateFlow<Property?>(null)
    val selectedProperty: StateFlow<Property?> get() = _selectedProperty

    fun fetchPropertyDetails(propertyId: Long) {
        viewModelScope.launch {
            Log.d("DetailViewModel", "Fetching property details for ID: $propertyId")
            val result = repository.getPropertyDetails(propertyId)

            if (result.isSuccess) {
                _selectedProperty.value = result.getOrNull()
                Log.d("DetailViewModel", "Property fetched: ${result.getOrNull()}")
            } else {
                Log.e("DetailViewModel", "Failed to fetch property: ${result.exceptionOrNull()?.message}")
            }
        }
    }
}
