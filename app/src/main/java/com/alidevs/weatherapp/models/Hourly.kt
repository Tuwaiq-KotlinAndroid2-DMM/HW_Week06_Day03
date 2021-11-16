package com.alidevs.weatherapp.models

data class Hourly(
    var clouds: Int,
    var dew_point: Double,
    var dt: Long,
    var feels_like: Double,
    var humidity: Int,
    var pop: Int,
    var pressure: Int,
    var temp: Double,
    var uvi: Double,
    var visibility: Int,
    var weather: List<WeatherDesc>,
    var wind_deg: Int,
    var wind_gust: Double,
    var wind_speed: Double,
)