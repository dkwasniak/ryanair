package com.damiankwasniak.ryanair.feature.stations.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.damiankwasniak.ryanair.feature.stations.model.StationModel
import com.damiankwasniak.ryanair.feature.stations.ui.adapter.StationsListRecyclerViewAdapter
import com.damiankwasniak.ryanair.ui.decorator.DividerRecyclerViewItemDecorator

class StationsRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var onStationSelected = fun(flight: StationModel) {}
        set(value) {
            stationsAdapter.onStationSelected = value
        }

    var items: List<StationModel> = listOf()
        set(value) {
            field = value
            stationsAdapter.items = value
            stationsAdapter.notifyDataSetChanged()
        }

    val stationsAdapter: StationsListRecyclerViewAdapter = StationsListRecyclerViewAdapter()

    init {
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerRecyclerViewItemDecorator(context))
        adapter = stationsAdapter
    }
}