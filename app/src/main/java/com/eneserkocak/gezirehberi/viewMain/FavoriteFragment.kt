package com.eneserkocak.gezirehberi.viewMain

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.adapter.*
import com.eneserkocak.gezirehberi.databinding.FragmentFavoriteBinding
import com.eneserkocak.gezirehberi.model.*
import com.eneserkocak.gezirehberi.room.*


class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    lateinit var dao: FavoriteParksDao
    private var favoriteParkList= listOf<FavoritePark>()

    lateinit var daoLake: FavoriteLakesDao
    private var favoriteLakeList= listOf<FavoriteLake>()

    lateinit var daoHistory: FavoriteHistoryDao
    private var favoriteHistoryList= listOf<FavoriteHistory>()

    lateinit var daoMuseum: FavoriteMuseumDao
    private var favoriteMuseumList= listOf<FavoriteMuseum>()

    lateinit var daoMosque: FavoriteMosqueDao
    private var favoriteMosqueList= listOf<FavoriteMosque>()

    lateinit var daoAncient: FavoriteAncientcityDao
    private var favoriteAncientList= listOf<FavoriteAncientcity>()

    lateinit var daoCaravanserai: FavoriteCaravanseraiDao
    private var favoriteCaravanseraiList= listOf<FavoriteCaravanserai>()

    lateinit var daoBath: FavoriteBathDao
    private var favoriteBathList= listOf<FavoriteBath>()

    lateinit var daoChurch: FavoriteChurchDao
    private var favoriteChurchList= listOf<FavoriteChurch>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //FAVORİ PARK LİSTESİ ADAPTER
        
        dao = FavoriteParksDatabase.getInstance(requireContext())!!.favoriteParksDao()
        favoriteParkList = dao.getAll()

        println("PARK LİSTESİ FAVORİ: ${favoriteParkList.size}")

       val favoriteParkListAdapter= FavoriteParkListAdapter(){
                viewModel.selectedFavoritePark.value=it
                findNavController().navigate(R.id.parkMapsFragment)
       }

        binding.favoriteParkListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteParkListRecycler.adapter= favoriteParkListAdapter


            favoriteParkListAdapter.updateFavoriteParkingList(favoriteParkList)

            if (favoriteParkList.isEmpty()){
            binding.parkLayout.visibility=View.GONE
        }else{
            binding.parkLayout.visibility=View.VISIBLE
            }



        //FAVORİ LAKE LİST ADAPTER

        daoLake = FavoriteLakesDatabase.getInstance(requireContext())!!.favoriteLakesDao()
        favoriteLakeList = daoLake.getAll()

        val favoriteLakeListAdapter= FavoriteLakeListAdapter(){
            viewModel.selectedFavoriteLake.value=it
            findNavController().navigate(R.id.lakeMapsFragment)
        }

        binding.favoriteLakeListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteLakeListRecycler.adapter= favoriteLakeListAdapter

        favoriteLakeListAdapter.updateFavoriteLakeList(favoriteLakeList)

        if (favoriteLakeList.isEmpty()){
            binding.lakeLayout.visibility=View.GONE
        }else{
            binding.lakeLayout.visibility=View.VISIBLE
        }


        //FAVORİ HİSTORY LİST ADAPTER

        daoHistory = FavoriteHistoryDatabase.getInstance(requireContext())!!.favoriteHistoryDao()
        favoriteHistoryList = daoHistory.getAll()

            val favoriteHistoryListAdapter= FavoriteHistoryListAdapter(){
            viewModel.selectedFavoriteHistory.value=it
            findNavController().navigate(R.id.historyMapsFragment)
        }

        binding.favoriteHistoryListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteHistoryListRecycler.adapter= favoriteHistoryListAdapter

        favoriteHistoryListAdapter.updateFavoriteHistoryList(favoriteHistoryList)

        if (favoriteHistoryList.isEmpty()){
            binding.historyLayout.visibility=View.GONE
        }else{
            binding.historyLayout.visibility=View.VISIBLE
        }



        //FAVORİ MUSEUM LİST ADAPTER

        daoMuseum = FavoriteMuseumDatabase.getInstance(requireContext())!!.favoriteMuseumDao()
        favoriteMuseumList = daoMuseum.getAll()



        val favoriteMuseumListAdapter= FavoriteMuseumListAdapter(){
            viewModel.selectedFavoriteMuseum.value=it
            findNavController().navigate(R.id.museumMapsFragment)
        }

        binding.favoriteMuseumListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteMuseumListRecycler.adapter= favoriteMuseumListAdapter

        favoriteMuseumListAdapter.updateFavoriteMuseumList(favoriteMuseumList)

        if (favoriteMuseumList.isEmpty()){
            binding.museumLayout.visibility=View.GONE
        }else{
            binding.museumLayout.visibility=View.VISIBLE
        }


        //FAVORİ MOSQUE LİST ADAPTER

        daoMosque = FavoriteMosqueDatabase.getInstance(requireContext())!!.favoriteMosqueDao()
        favoriteMosqueList = daoMosque.getAll()



        val favoriteMosqueListAdapter= FavoriteMosqueListAdapter(){
            viewModel.selectedFavoriteMosque.value=it
            findNavController().navigate(R.id.mosqueMapsFragment)
        }

        binding.favoriteMosqueListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteMosqueListRecycler.adapter= favoriteMosqueListAdapter

        favoriteMosqueListAdapter.updateFavoriteMosqueList(favoriteMosqueList)

        if (favoriteMosqueList.isEmpty()){
            binding.mosqueLayout.visibility=View.GONE
        }else{
            binding.mosqueLayout.visibility=View.VISIBLE
        }


        //FAVORİ ancientcity LİST ADAPTER

        daoAncient = FavoriteAncientcityDatabase.getInstance(requireContext())!!.favoriteAncientcityDao()
        favoriteAncientList = daoAncient.getAll()



        val favoriteAncientListAdapter= FavoriteAncientListAdapter(){
            viewModel.selectedFavoriteAnciencity.value=it
            findNavController().navigate(R.id.ancientMapsFragment)
        }

        binding.favoriteAncientListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteAncientListRecycler.adapter= favoriteAncientListAdapter

        favoriteAncientListAdapter.updateFavoriteAncientList(favoriteAncientList)

        if (favoriteAncientList.isEmpty()){
            binding.ancientLayout.visibility=View.GONE
        }else{
            binding.ancientLayout.visibility=View.VISIBLE
        }


        //FAVORİ CARAVANSERAİ LİST ADAPTER

        daoCaravanserai = FavoriteCaravanseraiDatabase.getInstance(requireContext())!!.favoriteCaravanseraiDao()
        favoriteCaravanseraiList = daoCaravanserai.getAll()



        val favoriteCaravanseraiListAdapter= FavoriteCaravanseraiListAdapter(){
            viewModel.selectedFavoriteCaravanserai.value=it
            findNavController().navigate(R.id.caravanseraiMapsFragment)
        }

        binding.favoriteCaravanseraiListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteCaravanseraiListRecycler.adapter= favoriteCaravanseraiListAdapter

        favoriteCaravanseraiListAdapter.updateFavoriteCaravanseraiList(favoriteCaravanseraiList)

        if (favoriteCaravanseraiList.isEmpty()){
            binding.caravanseraiLayout.visibility=View.GONE
        }else{
            binding.caravanseraiLayout.visibility=View.VISIBLE
        }



        //FAVORİ BATH LİST ADAPTER

        daoBath = FavoriteBathDatabase.getInstance(requireContext())!!.favoriteBathDao()
        favoriteBathList = daoBath.getAll()



        val favoriteBathListAdapter= FavoriteBathListAdapter(){
            viewModel.selectedFavoriteBath.value=it
            findNavController().navigate(R.id.bathMapsFragment)
        }

        binding.favoriteBathListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteBathListRecycler.adapter= favoriteBathListAdapter

        favoriteBathListAdapter.updateFavoriteBathList(favoriteBathList)

        if (favoriteBathList.isEmpty()){
            binding.bathLayout.visibility=View.GONE
        }else{
            binding.bathLayout.visibility=View.VISIBLE
        }



        //FAVORİ CHURCH LİST ADAPTER

        daoChurch = FavoriteChurchDatabase.getInstance(requireContext())!!.favoriteChurchDao()
        favoriteChurchList = daoChurch.getAll()



        val favoriteChurchListAdapter= FavoriteChurchListAdapter(){
            viewModel.selectedFavoriteChurch.value=it
            findNavController().navigate(R.id.churchMapsFragment)
        }

        binding.favoriteChurchListRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.favoriteChurchListRecycler.adapter= favoriteChurchListAdapter

        favoriteChurchListAdapter.updateFavoriteChurchList(favoriteChurchList)

        if (favoriteChurchList.isEmpty()){
            binding.churchLayout.visibility=View.GONE
        }else{
            binding.churchLayout.visibility=View.VISIBLE
        }
        

    }


}