package com.example.formtubes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.formtubes.databinding.ActivityMapsPelayananBinding

class MapsPelayanan : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsPelayananBinding
    private lateinit var dataRS: DataRS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsPelayananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        if (receivedIntent != null && receivedIntent.hasExtra(HomePage.DATARS_KEY)) {
             dataRS = receivedIntent.getParcelableExtra<DataRS>(HomePage.DATARS_KEY)!!
            // Lakukan sesuatu dengan objek DataRS yang diterima
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val rumahSakit = LatLng(dataRS.latitude,dataRS.longitude)
        mMap.addMarker(MarkerOptions().position(rumahSakit).title(dataRS.nama).snippet(dataRS.alamat))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(rumahSakit))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(rumahSakit,15f))
    }
}