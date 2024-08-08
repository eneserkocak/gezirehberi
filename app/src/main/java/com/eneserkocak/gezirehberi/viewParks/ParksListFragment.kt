package com.eneserkocak.gezirehberi.viewParks

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.ParksAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentParksListBinding
import com.eneserkocak.gezirehberi.model.Park
import com.eneserkocak.gezirehberi.viewMain.BaseFragment


class ParksListFragment : BaseFragment<FragmentParksListBinding>(R.layout.fragment_parks_list) {

     var parksList = mutableListOf<Park>()
    lateinit var adapter: ParksAdapter

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)

     adapter= ParksAdapter(){
        viewModel.selectedPark.value=it
         findNavController().navigate(R.id.parskDetailFragment)
     }

     observeLiveData()

     binding.parksRecycler.layoutManager= LinearLayoutManager(requireContext())
     binding.parksRecycler.adapter= adapter
 }

 fun observeLiveData(){

    viewModel.parks.observe(viewLifecycleOwner){ list->

        list?.let {
           parksList = it.toMutableList()

            val parkList= parksList.sortedBy{
                it.ilce
            }

            adapter.updateParkingList(parkList)



        }
    }
 }

}