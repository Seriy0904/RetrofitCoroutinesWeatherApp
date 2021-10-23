package dev.seriy.weatherapp.api

import dev.seriy.weatherapp.model.WeatherInfoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RequestInterface {
    @GET("/data/2.5/weather")
    @Headers("Content-type: application/json")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") key: String,
        @Query("lang") lang: String,
        @Query("units") units:String
    ): Response<WeatherInfoModel>
}