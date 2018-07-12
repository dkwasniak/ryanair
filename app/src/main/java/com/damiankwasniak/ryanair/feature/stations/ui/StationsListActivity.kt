package com.damiankwasniak.ryanair.feature.stations.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.feature.stations.model.StationListModel
import com.damiankwasniak.ryanair.feature.stations.model.StationModel
import com.damiankwasniak.ryanair.feature.stations.reducer.StationsReducer
import com.damiankwasniak.ryanair.feature.stations.reducer.StationsState
import com.damiankwasniak.ryanair.store
import com.damiankwasniak.setViewVisibility
import kotlinx.android.synthetic.main.activity_stations_list.*
import tw.geothings.rekotlin.StoreSubscriber
import android.content.Intent



class StationsListActivity : AppCompatActivity(), StoreSubscriber<StationsState> {

    companion object {

        private const val STATIONS_LIST = "stations_list"

        const val SELECTED_STATION = "selected_station"

        fun params(stationListModel: StationListModel): Bundle {
            val bundle = Bundle()
            bundle.putParcelable(STATIONS_LIST, stationListModel)
            return bundle
        }
    }

    val onStationSelected = fun(station: StationModel) {
        val returnIntent = Intent()
        returnIntent.putExtra(SELECTED_STATION, station)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stations_list)
        stationList = intent.getParcelableExtra(STATIONS_LIST)
        stationsRecyclerView.onStationSelected = onStationSelected
    }

    override fun newState(state: StationsState) {
        if (StationsReducer.stationsFetched(state)) {
            stationsRecyclerView.items = state.stationsList.stations
        }

        progressBar.setViewVisibility(StationsReducer.showProgress(state))
        stationsRecyclerView.setViewVisibility(!StationsReducer.showProgress(state))
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        store.subscribe(this) {
            it.select {
                it.stationsState
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        store.unsubscribe(this)
    }

    private var stationList: StationListModel? = null

    private var currentSelectionIndex = -1
}
