package com.damiankwasniak.ryanair.feature.searchresult.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.feature.searchresult.ui.adapter.SearchResultRecyclerViewAdapter
import com.damiankwasniak.ryanair.ui.decorator.DividerRecyclerViewItemDecorator

class SearchResultRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var onTripSelected = fun(station: FlightViewModel) {}
        set(value) {
            searchReusltAdapter.onTripSelected = value
        }

    var items: List<FlightViewModel> = listOf()
        set(value) {
            field = value
            searchReusltAdapter.items = value
            searchReusltAdapter.notifyDataSetChanged()
        }

    val searchReusltAdapter: SearchResultRecyclerViewAdapter = SearchResultRecyclerViewAdapter()

    init {
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerRecyclerViewItemDecorator(context))
        adapter = searchReusltAdapter
    }
}