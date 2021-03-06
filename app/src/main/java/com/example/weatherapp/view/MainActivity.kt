package com.example.weatherapp.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.LocationListAdapter
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*

const val   EXTRA_MESSAGE = "com.example.weatherapp.MESSAGE"
class MainActivity : AppCompatActivity() {

    private lateinit var button :Button
    public lateinit var textView1:TextView
    private  lateinit var nearCitybutton:Button

    lateinit var recyclerAdapter : LocationListAdapter
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.getLocationButton)
        textView1 = findViewById(R.id.lattlong)
        nearCitybutton = findViewById(R.id.nearCity)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        checkPermission()
        button.setOnClickListener {

            initRecyclerView()
            initViewModel()
        }

        nearCitybutton.setOnClickListener {
            val latt_long = textView1.text.toString()
            val intent =Intent(this,NearCityActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE,latt_long)
            }
            startActivity(intent)
        }
    }

    public fun checkPermission(){


        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),1)
        }
        else{
            getLocation()
        }
    }
    @SuppressLint("MissingPermission")
    public fun getLocation() {


        fusedLocationProviderClient.lastLocation?.addOnSuccessListener{
            if (it==null){
                Toast.makeText(this,"Sorry cant get location",Toast.LENGTH_SHORT).show()
            }
            else it.apply {
              val  latitude= it.latitude
              val longitude = it.longitude
                textView1.text="${latitude},${longitude}"


            }
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode==1){
            if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun initRecyclerView(){
        locationListRecyclerview.layoutManager= LinearLayoutManager(this)
        recyclerAdapter = LocationListAdapter()
        locationListRecyclerview.adapter = recyclerAdapter
    }

    private fun initViewModel(){
        val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if (it!=null){
                recyclerAdapter.setLocaitonList(it)
                recyclerAdapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this,"Error in getting list",Toast.LENGTH_SHORT).show()
            }

        })

        viewModel.makeApiCall(textView1.text.toString() )


    }
}