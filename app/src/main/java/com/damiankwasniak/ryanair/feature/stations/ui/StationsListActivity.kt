package com.damiankwasniak.ryanair.feature.stations.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.applicationComponent
import com.damiankwasniak.ryanair.feature.stations.model.StationModel
import com.damiankwasniak.ryanair.feature.stations.presenter.StationsActivityPresenter
import com.damiankwasniak.setViewVisibility
import kotlinx.android.synthetic.main.activity_stations_list.*
import javax.inject.Inject


class StationsListActivity : AppCompatActivity(), StationsView {

    companion object {

        const val SELECTED_STATION = "selected_station"
    }

    @Inject
    lateinit var stationsActivityPresenter: StationsActivityPresenter

    val onStationSelected = fun(station: StationModel) {
        val returnIntent = Intent()
        returnIntent.putExtra(SELECTED_STATION, station)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationComponent.inject(this)
        setContentView(R.layout.activity_stations_list)
        stationsRecyclerView.onStationSelected = onStationSelected
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        stationsActivityPresenter.attached(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stationsActivityPresenter.detached()
    }

    override fun showProgress(showProgress: Boolean) {
        progressBar.setViewVisibility(showProgress)
        stationsRecyclerView.setViewVisibility(!showProgress)
    }

    override fun setResult(stations: List<StationModel>) {
        stationsRecyclerView.items = stations
    }

    override fun showError(msg: Int) {

    }
}
