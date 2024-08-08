package com.eneserkocak.gezirehberi.viewLakes

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.LakesAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentLakesListBinding
import com.eneserkocak.gezirehberi.model.Lake
import com.eneserkocak.gezirehberi.viewMain.BaseFragment


class LakesListFragment : BaseFragment<FragmentLakesListBinding>(R.layout.fragment_lakes_list) {

        var lakesList= mutableListOf<Lake>()
        lateinit var adapter: LakesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        adapter=LakesAdapter(){
            viewModel.selectedLake.value= it
            findNavController().navigate(R.id.lakesDetailFragment)
        }

        binding.lakesRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.lakesRecycler.adapter= adapter
    }

    fun observeLiveData(){

        viewModel.lakes.observe(viewLifecycleOwner){ list->
            list?.let {
                lakesList =it.toMutableList()

                val lakeList= lakesList.sortedBy{
                    it.district
                }

                adapter.updateLakeList(lakeList)
            }
        }

    }


}