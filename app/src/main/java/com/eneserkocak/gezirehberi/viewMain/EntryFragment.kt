package com.eneserkocak.gezirehberi.viewMain

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.databinding.FragmentEntryBinding
import java.util.*
import kotlin.collections.ArrayList


class EntryFragment : BaseFragment<FragmentEntryBinding>(R.layout.fragment_entry) {







    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        binding.slaytText.isSelected=true

        binding.placeBtn.setOnClickListener {
                findNavController().navigate(R.id.placesFragment)
            }

        binding.favoriteBtn.setOnClickListener {
            findNavController().navigate(R.id.favoriteFragment)
        }

        binding.myCityBtn.setOnClickListener {
            findNavController().navigate(R.id.myCityFragment)
        }

        binding.weatherBtn.setOnClickListener {
            findNavController().navigate(R.id.weatherFragment)
        }


        //ANİMASYON KODLAR:
        val animasyon1 = AnimationUtils.loadAnimation(getContext(), R.anim.animasyon2)
        val animasyon2 = AnimationUtils.loadAnimation(getContext(), R.anim.animasyon1)

        val imageView2= binding.imageView2
        val imageView1= binding.imageView1

        imageView2.animation= animasyon1
        imageView1.animation=animasyon2
    //    loadData()



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


        var runnable=Runnable{}
        val handler= Handler(Looper.getMainLooper())

       runnable=object :Runnable{
            override fun run() {
                for (image in imageArray){

                    image.visibility=View.GONE

                }

                val random= Random()
                val randomIndex= random.nextInt(17)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,3000)

            }

        }
        handler.post(runnable)



    }









}