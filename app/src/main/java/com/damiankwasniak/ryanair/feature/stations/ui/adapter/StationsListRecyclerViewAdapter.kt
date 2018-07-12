package com.damiankwasniak.ryanair.feature.stations.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.feature.stations.model.StationModel
import kotlinx.android.synthetic.main.station_list_item.view.*

class StationsListRecyclerViewAdapter: RecyclerView.Adapter<StationsListRecyclerViewAdapter.StationViewHolder>() {

    var items: List<StationModel> = listOf()

    var onStationSelected = fun(station: StationModel) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_list_item, parent, false)
        return StationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class StationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(station: StationModel) {
            view.airPortNameTextView.text = station.name
            view.airPortCodeTextView.text = station.code
            view.airPortCountryNameTextView.text = station.countryName
            view.setOnClickListener {
                onStationSelected(station)
            }
        }
    }
}