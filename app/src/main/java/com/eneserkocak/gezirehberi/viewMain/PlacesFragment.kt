package com.eneserkocak.gezirehberi.viewMain

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.databinding.FragmentPlacesBinding

class PlacesFragment : BaseFragment<FragmentPlacesBinding>(R.layout.fragment_places) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.parksBtn.setOnClickListener {
            findNavController().navigate(R.id.parksListFragment)
        }


        binding.lakesBtn.setOnClickListener {
            findNavController().navigate(R.id.lakesListFragment)
        }

        binding.historicalBtn.setOnClickListener {
            findNavController().navigate(R.id.historyListFragment)
        }

        binding.museumsBtn.setOnClickListener {
            findNavController().navigate(R.id.museumListFragment)
        }

        binding.mosquesBtn.setOnClickListener {
            findNavController().navigate(R.id.mosqueListFragment)
        }

        binding.ancientcityBtn.setOnClickListener {
            findNavController().navigate(R.id.ancientListFragment)
        }

        binding.caravanseraiBtn.setOnClickListener {
            findNavController().navigate(R.id.caravanseraiListFragment)
        }

        binding.bathBtn.setOnClickListener {
            findNavController().navigate(R.id.bathListFragment)
        }

        binding.churchesBtn.setOnClickListener {
            findNavController().navigate(R.id.churchListFragment)
        }

    }
}