package edu.uga.cs.myrealestateapp.network

import edu.uga.cs.myrealestateapp.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertyApiService {
    @GET("property/detail")
    suspend fun getPropertyDetails(@Query("attomid") attomId: String): Response<ApiResponse>
}
