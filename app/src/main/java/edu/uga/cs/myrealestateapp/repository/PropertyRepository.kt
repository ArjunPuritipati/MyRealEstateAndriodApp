package edu.uga.cs.myrealestateapp.repository

import android.util.Log
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.network.RetrofitClient
import java.net.SocketTimeoutException

class PropertyRepository {

    suspend fun getProperties(
        postalCode: String,
        radius: Int,
        minBeds: Int,
        maxBeds: Int,
        minBaths: Int,
        maxBaths: Int,
        propertyType: String
    ): Result<List<Property>> {
        return try {
            val response = RetrofitClient.instance.fetchProperties(
                postalCode = postalCode,
                radius = radius,
                minBeds = minBeds,
                maxBeds = maxBeds,
                minBathsTotal = minBaths,
                maxBathsTotal = maxBaths,
                propertyType = propertyType
            )
            if (response.isSuccessful) {
                val apiResponse = response.body()
                val properties = apiResponse?.property.orEmpty()
                Log.d("PropertyRepository", "Fetched properties: $properties")
                Result.success(properties)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                Log.e("PropertyRepository", "Error fetching properties: $errorMessage")
                Result.failure(Exception("Error fetching properties: ${response.message()}"))
            }
        } catch (e: SocketTimeoutException) {
            Log.e("PropertyRepository", "Timeout fetching properties: ${e.message}")
            Result.failure(Exception("Request timed out. Please try again later."))
        } catch (e: Exception) {
            Log.e("PropertyRepository", "Exception fetching properties: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getPropertyDetails(propertyId: Long): Result<Property?> {
        return try {
            Log.d("PropertyRepository", "Fetching details for property ID: $propertyId")
            val response = RetrofitClient.instance.fetchPropertyDetails(propertyId)

            if (response.isSuccessful) {
                val apiResponse = response.body()
                val property = apiResponse?.property?.firstOrNull()
                Log.d("PropertyRepository", "Fetched property: $property")
                Result.success(property)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                Log.e("PropertyRepository", "Error fetching property details: $errorMessage")
                Result.failure(Exception("Error fetching property details: ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.e("PropertyRepository", "Exception: ${e.message}")
            Result.failure(e)
        }
    }
}
