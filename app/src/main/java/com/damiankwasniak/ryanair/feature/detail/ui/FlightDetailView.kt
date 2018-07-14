package com.damiankwasniak.ryanair.feature.detail.ui

import com.damiankwasniak.ryanair.ui.view.BaseView

interface FlightDetailView: BaseView {

    fun setFlightDate(flightDate: String)

    fun setRegularFare(flightRegularFare: Double, currency: String)

    fun setBusinessFare(flightBusinessFare: Double, currency: String)

    fun setLeisureFare(flightLeisureFare: Double, currency: String)

    fun noLeisureFareData()

    fun noBusinessFareData()
}