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
import org.w3c.dom.Text

const val   EXTRA_MESSAGE2 = "com.example.weatherapp.MESSAGE"
class NearCityActivity : AppCompatActivity() {


    private lateinit var recyclerAdapter:NearCityListAdapter
    private lateinit var textView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_near_city)

        val latt_long = intent.getStringExtra(EXTRA_MESSAGE)
        textView =findViewById<TextView?>(R.id.latt_long).apply {
            text=latt_long
        }


        initRecyclerView()
        initViewModel()

        fun goAct(woeid:String){

             val intent = Intent(this,CityDetailActivity::class.java).apply {
                 putExtra(EXTRA_MESSAGE2,woeid)
         }
                startActivity(intent)
        }


        recyclerAdapter.setOnItemClickListener(object :NearCityListAdapter.onItemClickListener{
            override fun onItemClick(position: TextView) {

                goAct(position.text.toString())

            }

        })


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