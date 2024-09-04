package com.eneserkocak.gezirehberi.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.eneserkocak.gezirehberi.model.*
import com.eneserkocak.gezirehberi.service.WeatherAPIService
import com.eneserkocak.gezirehberi.modelWeather.WeatherModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel: ViewModel() {



    val parks = MutableLiveData<List<Park>>()
    val selectedPark= MutableLiveData<Park>()
    val selectedFavoritePark= MutableLiveData<FavoritePark>()

    val lakes=MutableLiveData<List<Lake>>()
    val selectedLake=MutableLiveData<Lake>()
    val selectedFavoriteLake=MutableLiveData<FavoriteLake>()

    val history=MutableLiveData<List<History>>()
    val selectedHistory=MutableLiveData<History>()
    val selectedFavoriteHistory=MutableLiveData<FavoriteHistory>()

    val museum=MutableLiveData<List<Museum>>()
    val selectedMuseum=MutableLiveData<Museum>()
    val selectedFavoriteMuseum=MutableLiveData<FavoriteMuseum>()

    val mosque=MutableLiveData<List<Mosque>>()
    val selectedMosque=MutableLiveData<Mosque>()
    val selectedFavoriteMosque=MutableLiveData<FavoriteMosque>()

    val ancientcity=MutableLiveData<List<Ancientcity>>()
    val selectedAncientcity=MutableLiveData<Ancientcity>()
    val selectedFavoriteAnciencity=MutableLiveData<FavoriteAncientcity>()

    val caravanserai=MutableLiveData<List<Caravanserai>>()
    val selectedCaravanserai=MutableLiveData<Caravanserai>()
    val selectedFavoriteCaravanserai=MutableLiveData<FavoriteCaravanserai>()

    val bath=MutableLiveData<List<Bath>>()
    val selectedBath=MutableLiveData<Bath>()
    val selectedFavoriteBath=MutableLiveData<FavoriteBath>()

    val church=MutableLiveData<List<Church>>()
    val selectedChurch=MutableLiveData<Church>()
    val selectedFavoriteChurch=MutableLiveData<FavoriteChurch>()


    fun setData(context : Context) {

        val jsonFileString: String = context.assets.open("park.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<Park>>() {}.type

        var parkList: List<Park> = gson.fromJson(jsonFileString, listPersonType)


        parks.value= parkList

    }

    fun settingData(context : Context) {

        val jsonFileString: String = context.assets.open("lake.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<Lake>>() {}.type

        var lakeList: List<Lake> = gson.fromJson(jsonFileString, listPersonType)


        lakes.value= lakeList

    }


    fun setHistoryData(context : Context) {

        val jsonFileString: String = context.assets.open("historical.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<History>>() {}.type

        var historyList: List<History> = gson.fromJson(jsonFileString, listPersonType)


        history.value= historyList

    }

    fun setMuseumData(context : Context) {

        val jsonFileString: String = context.assets.open("museum.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<Museum>>() {}.type

        var museumList: List<Museum> = gson.fromJson(jsonFileString, listPersonType)


        museum.value= museumList

    }

    fun setMosqueData(context : Context) {

        val jsonFileString: String = context.assets.open("mosque.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<Mosque>>() {}.type

        var mosqueList: List<Mosque> = gson.fromJson(jsonFileString, listPersonType)


        mosque.value= mosqueList

    }


    fun setAncientcityData(context : Context) {

        val jsonFileString: String = context.assets.open("ancient.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<Ancientcity>>() {}.type

        var ancientcityList: List<Ancientcity> = gson.fromJson(jsonFileString, listPersonType)


        ancientcity.value= ancientcityList

    }

    fun setCaravanseraiData(context : Context) {

        val jsonFileString: String = context.assets.open("caravanserai.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<Caravanserai>>() {}.type

        var caravanseraiList: List<Caravanserai> = gson.fromJson(jsonFileString, listPersonType)


        caravanserai.value= caravanseraiList

    }

    fun setBathData(context : Context) {

        val jsonFileString: String = context.assets.open("bath.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<Bath>>() {}.type

        var bathList: List<Bath> = gson.fromJson(jsonFileString, listPersonType)


        bath.value= bathList

    }

    fun setChurchData(context : Context) {

        val jsonFileString: String = context.assets.open("church.json").bufferedReader().use {
            it.readText()
        }

        val gson = Gson()
        val listPersonType = object : TypeToken<List<Church>>() {}.type

        var churchList: List<Church> = gson.fromJson(jsonFileString, listPersonType)


        church.value= churchList

    }


    //WEATHER

    private val weatherAPIService= WeatherAPIService()
   // private val disposable = CompositeDisposable()

    val weather_data= MutableLiveData<WeatherModel>()
    val weather_error=MutableLiveData<Boolean>()
    val weather_load= MutableLiveData<Boolean>()

    fun refreshData(cityName: String){

        getDataFromAPI(cityName)

    }

   /* private fun getDataFromAPI(cityName:String) {

        weather_load.value=true
        disposable.add(
            weatherAPIService.getDataService(cityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<WeatherModel>(){
                    override fun onSuccess(t: WeatherModel) {
                        weather_data.value=t
                        weather_error.value=false
                    }

                    override fun onError(e: Throwable) {
                        weather_error.value=true
                        weather_load.value=false

                    }


                })

        )
    }*/


    private val compositeDisposable = CompositeDisposable()

    private fun getDataFromAPI(cityName: String) {
        weather_load.value = true
        val disposable = weatherAPIService.getDataService(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {
                override fun onSuccess(t: WeatherModel) {
                    weather_data.value = t
                    weather_error.value = false
                    weather_load.value = false
                }

                override fun onError(e: Throwable) {
                    weather_error.value = true
                    weather_load.value = false

                    Log.e("API Error", "Error fetching data", e)
                }
            })

        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

   /* fun getDataFromAPI(cityName: String) {
        weather_load.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    weatherAPIService.getDataService(cityName)
                }
                weather_data.value = response
                weather_error.value = false
            } catch (e: HttpException) {
                weather_error.value = true
                // Hata mesajını loglayabilirsiniz
                Log.e("WeatherAPI", "HTTP error fetching data for city: $cityName", e)
            } catch (e: Throwable) {
                weather_error.value = true
                // Diğer hata türleri için genel bir işlem
                Log.e("WeatherAPI", "Error fetching data for city: $cityName", e)
            } finally {
                weather_load.value = false
            }
        }
    }*/


}