package com.eneserkocak.gezirehberi.viewChurch

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.FragmentBathDetailBinding
import com.eneserkocak.gezirehberi.databinding.FragmentChurchDetailBinding
import com.eneserkocak.gezirehberi.model.Bath
import com.eneserkocak.gezirehberi.model.Church
import com.eneserkocak.gezirehberi.model.FavoriteBath
import com.eneserkocak.gezirehberi.model.FavoriteChurch
import com.eneserkocak.gezirehberi.room.FavoriteBathDatabase
import com.eneserkocak.gezirehberi.room.FavoriteChurchDatabase
import com.eneserkocak.gezirehberi.viewMain.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar


class ChurchDetailFragment : BaseFragment<FragmentChurchDetailBinding>(R.layout.fragment_church_detail),
    OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var sharedPreferences: SharedPreferences
    var trackBoolean: Boolean? = null
    lateinit var selectededChurch: Church

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapsChurch) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        sharedPreferences = requireActivity().getSharedPreferences("com.eneserkocak", AppCompatActivity.MODE_PRIVATE)
        trackBoolean = false

        observeLiveData()
        registerLauncher()


        viewModel.selectedChurch.observe(viewLifecycleOwner){
            it?.let { church->
                binding.webBtn.setOnClickListener {

                    goToWebAdressDialog()
                }
                binding.favoriBtn.setOnClickListener {

                    addFavoriteDialog()
                }
            }
        }
    }

    fun observeLiveData(){

        viewModel.selectedChurch.observe(viewLifecycleOwner){ selectedChurch->

            selectedChurch?.let {

                selectededChurch=it
                binding.churchName.text= it.placeName
                binding.churchExplanation.text= it.explanation
                AppUtil.pictureChurch(it,requireContext(),binding.churchDetailImage)

            }


        }

    }


    fun addFavoriteDialog(){

        val alert = AlertDialog.Builder(requireContext())
        alert.setMessage("Favori Listesine Eklensin mi?")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                val id = selectededChurch.id
                val name= selectededChurch.placeName
                val location= selectededChurch.district
                val lat = selectededChurch.lat
                val long= selectededChurch.long


                val favoriteChurch= FavoriteChurch(id,name,location,lat,long)

                viewModel.selectedFavoriteChurch.value= favoriteChurch
                FavoriteChurchDatabase.getInstance(requireContext())?.favoriteChurchDao()?.insertAll(favoriteChurch)

                findNavController().navigate(R.id.favoriteFragment)

            }
        })
        alert.setNegativeButton("HAYIR", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                // findNavController().popBackStack()
            }
        })

        alert.show()
    }

    fun goToWebAdressDialog(){

        val alert = AlertDialog.Builder(requireContext())
        alert.setMessage("Web Sayfasına Gidilsin mi?")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {


                val url = selectededChurch.web
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)



            }
        })
        alert.setNegativeButton("HAYIR", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                // findNavController().popBackStack()
            }
        })

        alert.show()
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap


        viewModel.selectedChurch.observe(viewLifecycleOwner) {
            it?.let {
                mMap.addMarker(MarkerOptions().position(LatLng(it.lat,it.long)).title(it.placeName)                    )
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(it.lat,it.long), 12f))
            }
        }


        /*  mMap.addMarker(MarkerOptions().position(LatLng(lat,long)).title("akyokus"))
          mMap.moveCamera(
              CameraUpdateFactory.newLatLngZoom(
                  LatLng(lat,long), 12f))*/

        locationManager =requireContext().getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager

        locationListener = object :
            LocationListener {override fun onLocationChanged(location: Location) {

            println(location.toString())

            trackBoolean = sharedPreferences.getBoolean("trackBoolean", false)
            if (trackBoolean == false) {

                val userLocation = LatLng(location.latitude, location.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12f))

                sharedPreferences.edit().putBoolean("trackBoolean", true).apply()

            }

        }

            override fun onProviderDisabled(provider: String) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

        }



        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            ) {
                Snackbar.make( binding.root,"Permission needed for location", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission") {
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

                }.show()

            } else {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }


        } else {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,0,0f, locationListener
            )

            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 12f))
            }
            mMap.isMyLocationEnabled = true

        }

    }



    private fun registerLauncher() {
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    //permission granted (izin verildi),(Yukarıda izin verilen yeri buraya da kopyala Aynı şeyi yapıp Konum isteyeceğiz)
                    if (ContextCompat.checkSelfPermission(requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)

                        val lastLocation =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if (lastLocation != null) {
                            val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,12f))}
                        mMap.isMyLocationEnabled = true

                    }


                } else {
                    Toast.makeText(requireContext(), "İzin vermeniz gerekiyor!", Toast.LENGTH_LONG).show()

                }
            }
    }
}
