package com.eneserkocak.gezirehberi.service

import com.eneserkocak.gezirehberi.modelWeather.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.openweathermap.org/data/2.5/weather?q=konya&APPID=911a31355563938b8f2b760b6ac1a320

interface WeatherAPI {

    // @GET("data/2.5/weather?q=konya&APPID=911a31355563938b8f2b760b6ac1a320")
    @GET("data/2.5/weather?&units=metric&APPID=911a31355563938b8f2b760b6ac1a320")

    fun getData(
        @Query("q") cityName:String
    ): Single<WeatherModel>

}

