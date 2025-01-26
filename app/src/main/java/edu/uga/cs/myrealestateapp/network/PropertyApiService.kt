package edu.uga.cs.myrealestateapp.network

import edu.uga.cs.myrealestateapp.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertyApiService {

    @GET("property/snapshot")
    suspend fun fetchProperties(
        @Query("postalcode") postalCode: String,
        @Query("radius") radius: Int,
        @Query("minBeds") minBeds: Int,
        @Query("maxBeds") maxBeds: Int,
        @Query("minBathsTotal") minBathsTotal: Int,
        @Query("maxBathsTotal") maxBathsTotal: Int,
        @Query("propertyType") propertyType: String
    ): Response<ApiResponse>

    @GET("property/detail")
    suspend fun fetchPropertyDetails(
        @Query("attomid") attomId: Long
    ): Response<ApiResponse>
}
