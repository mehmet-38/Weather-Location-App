package com.example.weatherapp.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.weatherapp.R
import com.example.weatherapp.adapter.NearCityListAdapter
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.example.weatherapp.viewmodel.NearCityActivityViewModel
import kotlinx.android.synthetic.main.activity_near_city.*

class NearCityActivity : AppCompatActivity() {


    private lateinit var recyclerAdapter:NearCityListAdapter
    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var cityDetailbutton :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_near_city)
        val latt_long = intent.getStringExtra(EXTRA_MESSAGE)
        textView =findViewById<TextView?>(R.id.latt_long).apply {
            text=latt_long
        }
        button = findViewById(R.id.getListButton)
        cityDetailbutton = findViewById(R.id.cityDetailButton)
        button.setOnClickListener {
            initRecyclerView()
            initViewModel()
        }
        cityDetailbutton.setOnClickListener {
            val intent = Intent(this,CityDetailActivity::class.java)
            startActivity(intent)
        }


    }


    private fun initRecyclerView(){
        cityListRecyclerView.layoutManager= LinearLayoutManager(this)
        recyclerAdapter = NearCityListAdapter()
        cityListRecyclerView.adapter=recyclerAdapter
    }
    private fun initViewModel(){
        val viewModel :NearCityActivityViewModel = ViewModelProvider(this).get(NearCityActivityViewModel::class.java)

        viewModel.getLiveDataObserver().observe(this, Observer {
            if (it!=null){
                recyclerAdapter.setLocaitonList(it)
                recyclerAdapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this,"Error getting list",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall(textView.text.toString())
    }

}