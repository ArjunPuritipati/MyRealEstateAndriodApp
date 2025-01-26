package edu.uga.cs.myrealestateapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/"

    private val gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // Increase connection timeout
                    .readTimeout(30, TimeUnit.SECONDS) // Increase read timeout
                    .writeTimeout(30, TimeUnit.SECONDS) // Increase write timeout
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("apikey", "4373345c2847ed18519c437577abd9fa") // Replace with your API key
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
