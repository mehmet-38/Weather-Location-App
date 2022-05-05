package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.LocationModel
import kotlinx.android.synthetic.main.near_city_list_row.view.*


class NearCityListAdapter: RecyclerView.Adapter<NearCityListAdapter.MyViewHolder>(){

    private var cityList:List<LocationModel>?=null

    private lateinit var  mListener :onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: TextView)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }
    fun setLocaitonList(locationList:List<LocationModel>?){
        this.cityList=locationList
    }

    // RecyclerView de listelenecek row tanimlamasi
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearCityListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.near_city_list_row,parent,false)
        return MyViewHolder(view,mListener)
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

    class MyViewHolder(view:View,listener: onItemClickListener):RecyclerView.ViewHolder(view){

        init {
            view.setOnClickListener {
                listener.onItemClick(woeid)
            }
        }

        val cityName = view.city
        val woeid = view.woeidText

        fun bind(data: LocationModel){

            cityName.text = data.title
            woeid.text=data.woeid
        }
    }
}