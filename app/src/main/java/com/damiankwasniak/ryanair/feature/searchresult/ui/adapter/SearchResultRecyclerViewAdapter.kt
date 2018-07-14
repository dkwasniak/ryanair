package com.damiankwasniak.ryanair.feature.searchresult.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import kotlinx.android.synthetic.main.search_result_list_item.view.*


class SearchResultRecyclerViewAdapter : RecyclerView.Adapter<SearchResultRecyclerViewAdapter.SearchResultViewHolder>() {

    var items: List<FlightViewModel> = listOf()

    var onTripSelected = fun(flight: FlightViewModel) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_list_item, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class SearchResultViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(flight: FlightViewModel) {
            view.flightNumberLabel.text = flight.flightNumber
            view.flightDurationLabel.text = flight.flightDuration
            view.flightDateLabel.text = flight.flightDate
            view.flightPriceLabel.text = String.format(view.context.getString(R.string.price), flight.flightRegularFare, flight.currency)
            view.setOnClickListener {
                onTripSelected(flight)
            }
        }
    }
}