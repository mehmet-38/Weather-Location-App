package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.LocationModel
import kotlinx.android.synthetic.main.location_list_row.view.*
import kotlinx.android.synthetic.main.near_city_list_row.view.*


class NearCityListAdapter: RecyclerView.Adapter<NearCityListAdapter.MyViewHolder>(){

    private var cityList:List<LocationModel>?=null

    fun setLocaitonList(locationList:List<LocationModel>?){
        this.cityList=locationList
    }

    // RecyclerView de listelenecek row tanimlamasi
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearCityListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.near_city_list_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: NearCityListAdapter.MyViewHolder, position: Int) {
        holder.bind(cityList?.get(position)!!)

    }

    override fun getItemCount(): Int {
        if (cityList==null){
            return 0
        }
        else {
            return cityList?.size!!
        }
    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view){

        val cityName = view.city

        fun bind(data: LocationModel){

            cityName.text = data.title
        }
    }
}