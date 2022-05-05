package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.WeatherModel
import kotlinx.android.synthetic.main.weather_list_row.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class WeatherListAdapter:RecyclerView.Adapter<WeatherListAdapter.MyViewHolder>() {
    var weatherList = mutableListOf<WeatherData>()



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherListAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_row,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: WeatherListAdapter.MyViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
    return weatherList.size
    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {


        val stateName = view.weather_state_name
        val tempName= view.weather_temp_name
        val windName = view.weather_wind
        val img = view.img_weather_pictures
        val date = view.date

    //https://www.metaweather.com/static/img/weather/ico/hr.ico
        fun bind(data:WeatherData){
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
            stateName.text=data.weather_state_name
            tempName.text=  df.format(data.the_temp).toString()+" °C"
            windName.text=   df.format(data.wind_speed).toString()
            date.text=data.applicable_date

            Glide.with(this.itemView)
                .load("https://www.metaweather.com/static/img/weather/ico/"+data.weather_state_abbr+".ico")
                .into(img)

        }
    }
}