package com.eneserkocak.gezirehberi.viewMosque

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.MosqueAdapter
import com.eneserkocak.gezirehberi.adapter.MuseumAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentMosqueListBinding
import com.eneserkocak.gezirehberi.model.Mosque
import com.eneserkocak.gezirehberi.model.Museum
import com.eneserkocak.gezirehberi.viewMain.BaseFragment


class MosqueListFragment : BaseFragment<FragmentMosqueListBinding>(R.layout.fragment_mosque_list) {


    var mosquePlacesList= mutableListOf<Mosque>()
    lateinit var adapter: MosqueAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        adapter= MosqueAdapter {
            viewModel.selectedMosque.value= it
            findNavController().navigate(R.id.mosqueDetailFragment)
        }

        binding.mosqueRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.mosqueRecycler.adapter = adapter
    }

    fun observeLiveData(){

        viewModel.mosque.observe(viewLifecycleOwner){ list->

            list?.let {
                mosquePlacesList= it.toMutableList()

                val mosqueList= mosquePlacesList.sortedBy{
                    it.district
                }

                adapter.updateMosqueList(mosqueList)

            }


        }
    }



}