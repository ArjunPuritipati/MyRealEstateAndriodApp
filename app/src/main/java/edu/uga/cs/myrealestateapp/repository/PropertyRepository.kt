package edu.uga.cs.myrealestateapp.repository

import edu.uga.cs.myrealestateapp.network.PropertyApiService

class PropertyRepository(private val apiService: PropertyApiService) {
    suspend fun getPropertyDetails(attomId: String) =
        apiService.getPropertyDetails(attomId)
}
