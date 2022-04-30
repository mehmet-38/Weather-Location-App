package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DeadObjectException
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.LocationListAdapter
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var button :Button
    private lateinit var textView1:TextView

    lateinit var recyclerAdapter : LocationListAdapter
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.getLocationButton)
        textView1 = findViewById(R.id.lattlong)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        checkPermission()
        button.setOnClickListener {

            initRecyclerView()
            initViewModel()
        }


    }

    private fun checkPermission(){


        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),1)
        }
        else{
            getLocation()
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLocation() {


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
        Toast.makeText(this,"${textView1.text.toString()}",Toast.LENGTH_SHORT).show()

    }
}