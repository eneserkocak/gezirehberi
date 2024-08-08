package com.eneserkocak.gezirehberi.viewMain

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eneserkocak.gezirehberi.viewModel.AppViewModel
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NavController.OnDestinationChangedListener {


     val viewModel : AppViewModel by viewModels()
     lateinit var binding: ActivityMainBinding

    lateinit var navController : NavController

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


         binding.supportBtn.setOnClickListener {
             Navigation.findNavController(this, R.id.fragmentContainerView).navigate(R.id.supportFragment)
         }

        setNavController()

         viewModel.setData(this)
         viewModel.settingData(this)
         viewModel.setHistoryData(this)
         viewModel.setMuseumData(this)
         viewModel.setMosqueData(this)
         viewModel.setAncientcityData(this)
         viewModel.setCaravanseraiData(this)
         viewModel.setBathData(this)
         viewModel.setChurchData(this)


    }

   fun setNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(binding.materialToolbar)
        setupActionBarWithNavController(navController) //title
        binding.materialToolbar.setupWithNavController(navController) // up
        navController.addOnDestinationChangedListener(this)

    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ){
        binding.materialToolbar.setNavigationIcon(R.drawable.back)

        if (destination.id== R.id.entryFragment) binding.materialToolbar.setNavigationIcon(R.drawable.home_24)

        if(destination.id== R.id.entryFragment) binding.girisDestination.visibility=View.VISIBLE
        else binding.girisDestination.visibility=View.GONE



    }
}