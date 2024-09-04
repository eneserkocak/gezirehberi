package com.eneserkocak.gezirehberi.viewMain

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.databinding.FragmentEntryBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class EntryFragment : BaseFragment<FragmentEntryBinding>(R.layout.fragment_entry) {

    lateinit var job: Job

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.slaytText.isSelected = true

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

        val imageView2 = binding.imageView2
        val imageView1 = binding.imageView1

        imageView2.animation = animasyon1
        imageView1.animation = animasyon2
        //    loadData()

        showImages()

    }
    fun showImages() {


        val photos = listOf(
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9,
            R.drawable.p10,
            R.drawable.p11,
            R.drawable.p12,
            R.drawable.p13,
            R.drawable.p14,
            R.drawable.p15,
            R.drawable.p16,
            R.drawable.p17,
            R.drawable.p18,
            R.drawable.p19,
            R.drawable.p20,
        )
       job =  lifecycleScope.launch() {
            while (isActive) {
                binding.fotoImageV.setImageResource(photos.random())
             //   println("photo değişti")
                delay(3000)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }
}