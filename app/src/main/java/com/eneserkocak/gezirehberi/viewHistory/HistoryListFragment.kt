package com.eneserkocak.gezirehberi.viewHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.HistoryAdapter
import com.eneserkocak.gezirehberi.databinding.FragmentHistoryListBinding
import com.eneserkocak.gezirehberi.model.History
import com.eneserkocak.gezirehberi.viewMain.BaseFragment


class HistoryListFragment : BaseFragment<FragmentHistoryListBinding>(R.layout.fragment_history_list) {

        var historyPlacesList= mutableListOf<History>()
        lateinit var adapter: HistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            observeLiveData()
            adapter= HistoryAdapter {
                viewModel.selectedHistory.value= it
                findNavController().navigate(R.id.historyDetailFragment)
            }

        binding.historyPlacesRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.historyPlacesRecycler.adapter= adapter


    }

    fun observeLiveData(){

        viewModel.history.observe(viewLifecycleOwner){ list->

            list?.let {
                historyPlacesList= it.toMutableList()

                val historyList= historyPlacesList.sortedBy{
                    it.district
                }

                adapter.updateHistoryList(historyList)
            }

        }
    }
}