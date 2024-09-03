package com.example.shade.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://currentuvindex.com/api/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface StopLightApiService {
    @GET("uvi")
    suspend fun getPhotos(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): UvResponse
}

object IndexApi {
    val retrofitService : StopLightApiService by lazy {
        retrofit.create(StopLightApiService::class.java)
    }
}
