package com.eneserkocak.gezirehberi.viewCaravanserai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.CaravanseraiAdapter
import com.eneserkocak.gezirehberi.adapter.MosqueAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentCaravanseraiListBinding
import com.eneserkocak.gezirehberi.model.Caravanserai
import com.eneserkocak.gezirehberi.model.Mosque
import com.eneserkocak.gezirehberi.viewMain.BaseFragment


class CaravanseraiListFragment : BaseFragment<FragmentCaravanseraiListBinding>(R.layout.fragment_caravanserai_list) {

    var caravanseraisList= mutableListOf<Caravanserai>()
    lateinit var adapter: CaravanseraiAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeLiveData()

        adapter= CaravanseraiAdapter {
            viewModel.selectedCaravanserai.value= it
            findNavController().navigate(R.id.caravanseraiDetailFragment)
        }

        binding.caravanseraiRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.caravanseraiRecycler.adapter = adapter
    }

    fun observeLiveData(){

        viewModel.caravanserai.observe(viewLifecycleOwner){ list->

            list?.let {
                caravanseraisList= it.toMutableList()

                val caravanseraiList= caravanseraisList.sortedBy{
                    it.district
                }

                adapter.updateCaravanseraiList(caravanseraiList)

            }


        }
    }



}