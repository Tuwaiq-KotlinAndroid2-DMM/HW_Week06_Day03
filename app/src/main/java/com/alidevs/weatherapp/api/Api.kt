package com.alidevs.weatherapp.api

import com.alidevs.weatherapp.models.Weather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        var retrofitService: Api? = null

        fun getInstance() : Api {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(Api::class.java)
            }
            return retrofitService!!
        }
    }

    @GET("onecall?lat=26.420683&lon=50.088795&units=metric&appid=e7f3c24ee37fe2bdddce40009fc37dbc")
    fun oneCall() : Call<Weather>
}