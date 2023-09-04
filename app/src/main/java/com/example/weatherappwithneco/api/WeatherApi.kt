package com.example.weatherappwithneco.api


import com.example.weatherappwithneco.data.WeatherModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherApi {


    @GET("current.json")
  suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("aqi") aqi: String,
        @Query("key") api_key: String

    ): Call<WeatherModel>

    companion object {


        val api_key = "ddeca48e56ed4a83a64122512232008"

    }
}




