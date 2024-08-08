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
      val photos = listOf(
          R.drawable.a,
          R.drawable.b,
          R.drawable.c,
          R.drawable.kugulu,
          R.drawable.kil,
          R.drawable.e,
          R.drawable.ker,
          R.drawable.ks,
          R.drawable.p,
          R.drawable.pa,
          R.drawable.par,
          R.drawable.park,
          R.drawable.parks,
          R.drawable.parkss,
          R.drawable.parksss,
          R.drawable.parkssss,
          R.drawable.parksssss,
      )

        var runnable=Runnable{}
        val handler= Handler(Looper.getMainLooper())

       runnable=object :Runnable{
            override fun run() {
                binding.fotoImageV.setImageResource(photos.random())

                handler.postDelayed(runnable,3000)

            }

        }
        handler.post(runnable)



    }









}