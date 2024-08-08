package com.eneserkocak.gezirehberi.viewAncientcity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.AncientAdapter
import com.eneserkocak.gezirehberi.adapter.MosqueAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentAncientListBinding
import com.eneserkocak.gezirehberi.model.Ancientcity
import com.eneserkocak.gezirehberi.model.Mosque
import com.eneserkocak.gezirehberi.viewMain.BaseFragment


class AncientListFragment : BaseFragment<FragmentAncientListBinding>(R.layout.fragment_ancient_list) {


    var ancientsList= mutableListOf<Ancientcity>()
    lateinit var adapter: AncientAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        adapter= AncientAdapter() {
            viewModel.selectedAncientcity.value= it
            findNavController().navigate(R.id.ancientDetailFragment)
        }

        binding.ancientRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.ancientRecycler.adapter = adapter
    }

    fun observeLiveData(){

        viewModel.ancientcity.observe(viewLifecycleOwner){ list->

            list?.let {
                ancientsList= it.toMutableList()

                val ancientList= ancientsList.sortedBy{
                    it.district
                }

                adapter.updateAncientList(ancientList)

            }


        }
    }


}