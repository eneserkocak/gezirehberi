package com.eneserkocak.gezirehberi.viewMuseum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.HistoryAdapter
import com.eneserkocak.gezirehberi.adapter.MuseumAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentMuseumListBinding
import com.eneserkocak.gezirehberi.model.History
import com.eneserkocak.gezirehberi.model.Museum
import com.eneserkocak.gezirehberi.viewMain.BaseFragment


class MuseumListFragment : BaseFragment<FragmentMuseumListBinding>(R.layout.fragment_museum_list) {

    var museumPlacesList= mutableListOf<Museum>()
    lateinit var adapter: MuseumAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeLiveData()
        adapter= MuseumAdapter {
            viewModel.selectedMuseum.value= it
            findNavController().navigate(R.id.museumDetailFragment)
        }

        binding.museumsRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.museumsRecycler.adapter= adapter


    }

    fun observeLiveData(){

        viewModel.museum.observe(viewLifecycleOwner){ list->

            list?.let {
                museumPlacesList= it.toMutableList()

                val museumList= museumPlacesList.sortedBy{
                    it.district
                }

                adapter.updateMuseumList(museumList)
            }

        }
    }
}