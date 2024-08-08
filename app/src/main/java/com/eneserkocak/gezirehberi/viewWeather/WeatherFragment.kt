package com.eneserkocak.gezirehberi.viewWeather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.FragmentWeatherBinding
import com.eneserkocak.gezirehberi.viewMain.BaseFragment
import com.eneserkocak.gezirehberi.viewModel.AppViewModel


class WeatherFragment : BaseFragment<FragmentWeatherBinding>(R.layout.fragment_weather) {


    //https://api.openweathermap.org/data/2.5/weather?q=konya&APPID=911a31355563938b8f2b760b6ac1a320

    lateinit var viewmodel: AppViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewmodel= ViewModelProvider(this).get(AppViewModel::class.java)

        val sharedPref = AppUtil.getSharedPreferences(requireContext())
        var cName=sharedPref.getString("cityName", "konya")
        binding.edtCityName.setText(cName)


        viewmodel.refreshData(cName!!)

        getLiveData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.weatherView.visibility=View.GONE
            binding.tvError.visibility=View.GONE
            binding.pbLoading.visibility=View.GONE


            var cityName= sharedPref.getString("cityName", cName)
            binding.edtCityName.setText(cityName)

            viewmodel.refreshData(cityName!!)
            binding.swipeRefreshLayout.isRefreshing=false
        }

        binding.imgSearchCityName.setOnClickListener {

            val cityName= binding.edtCityName.text.toString()

            val sharedPreferences = AppUtil.getSharedPreferences(requireContext())
            sharedPreferences.edit().putString("cityName",cityName).apply()

            viewmodel.refreshData(cityName)

            getLiveData()

        }


    }

    private fun getLiveData() {
        viewmodel.weather_data.observe(viewLifecycleOwner){ data->

            data?.let {

                binding.weatherView.visibility= View.VISIBLE
                binding.pbLoading.visibility=View.GONE
                binding.tvDegree.text= data.main.temp.toString() + "°C"
                binding.tvCountryCode.text= data.sys.country.toString()
                binding.tvCityName.text= data.name.toString()
                binding.tvHumidity.text=":"+ "  "+data.main.humidity.toString()+" "+ "%"
                binding.tvSpeed.text=":"+ "  "+data.wind.speed.toString()+" "+"km/sa"
                binding.tvLat.text=":"+ "  "+data.coord.lat.toString()
                binding.tvLong.text=":"+ "  "+data.coord.lon.toString()

/*
                binding.tvPressure.text=":"+ "  "+data.main.grnd_level.toString()
                binding.tvLevel.text=":"+ "  "+data.main.temp_max.toString()*/

                //https://openweathermap.org/img/wn/10d@2x.png

                Glide.with(this).load("http://openweathermap.org/img/wn/" + data.weather.get(0).icon +"@2x.png")
                    .into(binding.imgWeatherIcon)
            }

        }

        viewmodel.weather_load.observe(viewLifecycleOwner){ load->

            load?.let {

                if (it){
                    binding.pbLoading.visibility=View.VISIBLE
                    binding.tvError.visibility=View.GONE
                    binding.weatherView.visibility=View.GONE
                }else{
                    binding.pbLoading.visibility= View.GONE
                }

            }

        }

        viewmodel.weather_error.observe(viewLifecycleOwner){ error->

            error?.let {

                if (it){
                    binding.pbLoading.visibility=View.GONE
                    binding.tvError.visibility=View.VISIBLE
                    binding.weatherView.visibility=View.GONE
                }else{
                    binding.tvError.visibility= View.GONE
                }

            }


        }
    }


}