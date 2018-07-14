package com.damiankwasniak.ryanair.feature.detail.presenter

import com.damiankwasniak.ryanair.feature.detail.ui.FlightDetailView
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.ui.presenter.BasePresenter
import javax.inject.Inject

class FlightDetailActivityPresenter @Inject constructor() : BasePresenter<FlightDetailView>() {

    private var flightViewModel: FlightViewModel? = null


    fun setFlightViewModel(flightViewModel: FlightViewModel?) {
        this.flightViewModel = flightViewModel
        flightViewModel?.let {
            view?.bindTripDate(it)
        }

    }
}