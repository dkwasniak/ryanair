package com.damiankwasniak.ryanair.feature.detail.ui

import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.ui.view.BaseView

interface FlightDetailView: BaseView {

    fun bindTripDate(it: FlightViewModel)

}