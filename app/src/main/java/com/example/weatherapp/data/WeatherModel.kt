package com.example.weatherapp.data



 data class WeatherModel(
     val consolidated_weather:List<WeatherData>

 )
data class WeatherData(

    val weather_state_name:String?,
    val the_temp:Float?,
    val wind_speed:Float?,
    val weather_state_abbr:String?,
    val applicable_date:String?
)



