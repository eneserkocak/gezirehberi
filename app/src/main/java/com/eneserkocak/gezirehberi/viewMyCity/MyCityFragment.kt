package com.eneserkocak.gezirehberi.viewMyCity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.databinding.FragmentMyCityBinding
import com.eneserkocak.gezirehberi.viewMain.BaseFragment
import java.util.*
import kotlin.collections.ArrayList

class MyCityFragment : BaseFragment<FragmentMyCityBinding>(R.layout.fragment_my_city) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.slaytText.isSelected=true

            binding.cultureBtn.setOnClickListener {
                findNavController().navigate(R.id.cultureFragment)
            }

            binding.populationBtn.setOnClickListener {
                findNavController().navigate(R.id.populationFragment)
        }

            binding.geographyBtn.setOnClickListener {
                findNavController().navigate(R.id.geographyFragment)
        }
            binding.economyBtn.setOnClickListener {
                findNavController().navigate(R.id.economyFragment)
            }

        binding.historyBtn.setOnClickListener {
            findNavController().navigate(R.id.historyFragment)
        }

        binding.kitchenBtn.setOnClickListener {
            findNavController().navigate(R.id.kitchenFragment)
        }

        binding.educationBtn.setOnClickListener {
            findNavController().navigate(R.id.educationFragment)
        }

        binding.transportBtn.setOnClickListener {
            findNavController().navigate(R.id.transportFragment)
        }

        binding.sportBtn.setOnClickListener {
            findNavController().navigate(R.id.sportFragment)
        }


    showImages()

    }

    fun showImages(){

        //Runnable Kodlar
        var imageArray=ArrayList<ImageView>()

        //RUNNABLE IMAGE LAR
        imageArray.add(binding.imageView26)
        imageArray.add(binding.imageView27)
        imageArray.add(binding.imageView28)
        imageArray.add(binding.imageView29)
        imageArray.add(binding.imageView30)
        imageArray.add(binding.imageView31)
        imageArray.add(binding.imageView32)
        imageArray.add(binding.imageView33)
        imageArray.add(binding.imageView34)
        imageArray.add(binding.imageView35)
        imageArray.add(binding.imageView36)
        imageArray.add(binding.imageView37)
        imageArray.add(binding.imageView38)
        imageArray.add(binding.imageView39)
        imageArray.add(binding.imageView40)
        imageArray.add(binding.imageView41)
        imageArray.add(binding.imageView42)
        imageArray.add(binding.imageView43)
        imageArray.add(binding.imageView44)
        imageArray.add(binding.imageView45)


        var runnable=Runnable{}
        val handler= Handler(Looper.getMainLooper())

        runnable=object :Runnable{
            override fun run() {
                for (image in imageArray){

                    image.visibility=View.GONE

                }

                val random= Random()
                val randomIndex= random.nextInt(20)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,3000)

            }

        }
        handler.post(runnable)



    }
}