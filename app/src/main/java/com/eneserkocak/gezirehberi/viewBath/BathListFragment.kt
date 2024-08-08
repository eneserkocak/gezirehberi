package com.eneserkocak.gezirehberi.viewBath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.BathAdapter
import com.eneserkocak.gezirehberi.adapter.MosqueAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentBathListBinding
import com.eneserkocak.gezirehberi.databinding.FragmentMosqueListBinding
import com.eneserkocak.gezirehberi.model.Bath
import com.eneserkocak.gezirehberi.model.Mosque
import com.eneserkocak.gezirehberi.viewMain.BaseFragment

class BathListFragment : BaseFragment<FragmentBathListBinding>(R.layout.fragment_bath_list) {


    var bathsList= mutableListOf<Bath>()
    lateinit var adapter: BathAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        adapter= BathAdapter {
            viewModel.selectedBath.value= it
            findNavController().navigate(R.id.bathDetailFragment)
        }

        binding.bathPlacesRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.bathPlacesRecycler.adapter = adapter
    }

    fun observeLiveData(){

        viewModel.bath.observe(viewLifecycleOwner){ list->

            list?.let {
                bathsList= it.toMutableList()

                val bathList= bathsList.sortedBy{
                    it.district
                }
                adapter.updateBathList(bathList)

            }


        }
    }



}