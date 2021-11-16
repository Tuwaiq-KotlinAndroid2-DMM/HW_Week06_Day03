package com.alidevs.weatherapp.models

data class Current(
    var clouds: Int,
    var dew_point: Double,
    var dt: Int,
    var feels_like: Double,
    var humidity: Int,
    var pressure: Int,
    var sunrise: Int,
    var sunset: Int,
    var temp: Double,
    var uvi: Int,
    var visibility: Int,
    var weather: List<WeatherDesc>,
    var wind_deg: Int,
    var wind_speed: Double
)