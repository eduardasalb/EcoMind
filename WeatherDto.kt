package com.example.ecomind.model

// data/model/WeatherDto.kt
data class WeatherDto(
    val main: MainDto,
    val wind: WindDto,
    val weather: List<WeatherDescDto>,
    val name: String?,
    val dt: Long
)
data class MainDto(val temp: Double, val feels_like: Double, val humidity: Int)
data class WindDto(val speed: Double)
data class WeatherDescDto(val main: String, val description: String)

