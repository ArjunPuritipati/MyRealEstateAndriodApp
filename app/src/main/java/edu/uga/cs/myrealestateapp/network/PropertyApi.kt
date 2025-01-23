package edu.uga.cs.myrealestateapp.network

import edu.uga.cs.myrealestateapp.model.Property
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PropertyApi {
    @GET("property/detail")
    suspend fun getPropertyDetails(
        @Query("attomid") attomId: String,
        @Header("apikey") apiKey: String = "73b3e941adce0e0de6c8f635291e6b36"
    ): Response<Property>
}
