package com.alidevs.weatherapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.alidevs.weatherapp.adapters.HourlyAdapter
import com.alidevs.weatherapp.api.Api
import com.alidevs.weatherapp.databinding.ActivityMainBinding
import com.alidevs.weatherapp.models.Weather
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var hourlyAdapter: HourlyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.hourlyRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val liveData = MutableLiveData<Weather>()

        val api = Api.getInstance()
        api.oneCall().enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Successful request", Toast.LENGTH_SHORT)
                        .show()
                    liveData.postValue(response.body())
                } else {
                    Toast.makeText(this@MainActivity, "Failure request", Toast.LENGTH_SHORT).show()
                    Log.d("Retrofit", call.request().url().toString())
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                t.message?.let { Log.d("Retrofit", t.message!!) }
            }
        })

        liveData.observe(this, {
            with(binding) {
                weatherStatusTextView.text = it.current.weather[0].main
                weatherTempTextView.text = "${it.current.temp}°"
                windTextView.text = "${it.current.wind_speed} km/h"
                feelsLikeTextView.text = "${it.current.feels_like}°"
                pressureTextView.text = "${it.current.pressure} mbar"
                humidityTextView.text = "${it.current.humidity}%"
                Log.d(
                    "Picasso",
                    "http://openweathermap.org/img/wn/${it.current.weather[0].icon}@4x.png"
                )
                Picasso
                    .get()
                    .load("http://openweathermap.org/img/wn/${it.current.weather[0].icon}@4x.png")
                    .into(weatherIconImageView)

                hourlyRecyclerView.adapter = HourlyAdapter(it.hourly)
            }
        })

    }
}