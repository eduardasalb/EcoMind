package com.example.ecomind.network

import retrofit2.http.GET
import retrofit2.http.Query

// Ajuste os campos do response conforme a API real que você consome
data class WeatherResponse(
    val name: String?,
    val main: Main?
)

data class Main(
    val temp: Double?
)

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}
