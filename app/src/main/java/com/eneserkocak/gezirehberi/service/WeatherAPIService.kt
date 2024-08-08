package com.eneserkocak.gezirehberi.service

import com.eneserkocak.gezirehberi.modelWeather.WeatherModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//https://api.openweathermap.org/data/2.5/weather?q=konya&APPID=911a31355563938b8f2b760b6ac1a320


class WeatherAPIService {

    private val BASE_URL ="https://api.openweathermap.org"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)


    fun getDataService(cityName:String): Single<WeatherModel> {

        return api.getData(cityName)

    }

}

