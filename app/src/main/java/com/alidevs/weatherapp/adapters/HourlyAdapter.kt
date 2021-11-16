package com.alidevs.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alidevs.weatherapp.databinding.HourlyTempCellBinding
import com.alidevs.weatherapp.models.Hourly
import com.alidevs.weatherapp.models.Weather
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class HourlyAdapter(var data: List<Hourly>): RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    private lateinit var binding: HourlyTempCellBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = HourlyTempCellBinding.inflate(inflater)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(hourly: Hourly) {
            binding.cellTempTextView.text = hourly.temp.toString()
            binding.cellTimeTextView.text = hourly.dt.convertLongToTime()
            Picasso
                .get()
                .load("http://openweathermap.org/img/wn/${hourly.weather[0].icon}@4x.png")
                .into(binding.cellWeatherIconImageView)
        }

    }

}

fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm")
    return format.format(date)
}