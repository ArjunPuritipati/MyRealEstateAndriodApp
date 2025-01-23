package edu.uga.cs.myrealestateapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/"

    // Create a Gson instance with custom deserialization logic if needed
    private val gson by lazy {
        GsonBuilder()
            .setLenient() // Makes the parser lenient for potential formatting issues
            .create()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Use the Gson instance
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("apikey", "73b3e941adce0e0de6c8f635291e6b36") // Replace with your actual API key
                            .build()
                        chain.proceed(request)
                    }
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
    }

    val instance: PropertyApiService by lazy {
        retrofit.create(PropertyApiService::class.java)
    }
}
