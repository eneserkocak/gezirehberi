package com.eneserkocak.gezirehberi.viewChurch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.BathAdapter
import com.eneserkocak.gezirehberi.adapter.ChurchAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentBathListBinding
import com.eneserkocak.gezirehberi.databinding.FragmentChurchListBinding
import com.eneserkocak.gezirehberi.model.Bath
import com.eneserkocak.gezirehberi.model.Church
import com.eneserkocak.gezirehberi.viewMain.BaseFragment


class ChurchListFragment : BaseFragment<FragmentChurchListBinding>(R.layout.fragment_church_list) {


    var churchsList= mutableListOf<Church>()
    lateinit var adapter: ChurchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        adapter= ChurchAdapter {
            viewModel.selectedChurch.value= it
            findNavController().navigate(R.id.churchDetailFragment)
        }

        binding.churchPlacesRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.churchPlacesRecycler.adapter = adapter
    }

    fun observeLiveData(){

        viewModel.church.observe(viewLifecycleOwner){ list->

            list?.let {
                churchsList= it.toMutableList()

                val churchList= churchsList.sortedBy{
                    it.district
                }

                adapter.updateChurchList(churchList)

            }


        }
    }



}