package com.example.weatherapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.LocationModel
import kotlinx.android.synthetic.main.location_list_row.view.*

class LocationListAdapter: RecyclerView.Adapter<LocationListAdapter.MyViewHolder>(){

    private var locationList:List<LocationModel>?=null

    fun setLocaitonList(locationList:List<LocationModel>?){
        this.locationList=locationList
    }

    // RecyclerView de listelenecek row tanimlamasi
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_list_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationListAdapter.MyViewHolder, position: Int) {
        holder.bind(locationList?.get(position)!!)

    }

    override fun getItemCount(): Int {
        if (locationList==null){
            return 0
        }
        else {
            return locationList?.size!!
        }
    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view){

        val distance = view.distance
        val title = view.title
        val location_type = view.locationType
        val latt_long = view.latt_long

        fun bind(data: LocationModel){
            distance.text= "Location Distance : "+ data.distance
            title.text = "Location Name : "+ data.title
            location_type.text= "Location Type : "+ data.location_type
            latt_long.text = "Location latt - long : "+data.latt_long


        }
    }
}