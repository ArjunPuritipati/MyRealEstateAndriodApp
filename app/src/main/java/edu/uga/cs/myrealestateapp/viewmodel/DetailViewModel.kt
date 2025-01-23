package edu.uga.cs.myrealestateapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uga.cs.myrealestateapp.model.ApiResponse
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _propertyDetails = MutableStateFlow<Property?>(null)
    val propertyDetails: StateFlow<Property?> get() = _propertyDetails

    fun fetchPropertyDetails(attomId: String) {
        viewModelScope.launch {
            try {
                Log.d("DetailViewModel", "Fetching property details for Attom ID: $attomId")
                val response = RetrofitClient.instance.getPropertyDetails(attomId)
                if (response.isSuccessful) {
                    val apiResponse: ApiResponse? = response.body()
                    Log.d("DetailViewModel", "Raw JSON Response: ${response.body()}")
                    _propertyDetails.value = apiResponse?.property?.firstOrNull()
                    Log.d("DetailViewModel", "Property details fetched: ${_propertyDetails.value}")
                } else {
                    Log.e("DetailViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("DetailViewModel", "Exception: ${e.message}", e)
            }
        }
    }
}
