package com.areeb.weatherunion.data.api.lat_lon_weather_data

interface LatLonWeatherDataApi {
    fun getWeatherData(lat: Float, lon: Float): String
}
