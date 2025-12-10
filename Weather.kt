package com.example.ecomind.model

data class Weather(
    val city: String?,
    val tempC: Double,
    val feelsLikeC: Double,
    val humidity: Int,
    val windSpeed: Double,
    val condition: String,
    val timestamp: Long
)