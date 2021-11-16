package com.alidevs.weatherapp.models

data class Weather(
    var current: Current,
    var hourly: List<Hourly>,
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var timezone_offset: Int
)