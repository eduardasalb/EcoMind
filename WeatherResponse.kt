package com.example.ecomind.api

data class WeatherResponse(
    val name: String,
    val main: MainInfo,
    val weather: List<WeatherInfo>
)

data class MainInfo(
    val temp: Float,
    val humidity: Int
)

data class WeatherInfo(
    val description: String,
    val icon: String
)
