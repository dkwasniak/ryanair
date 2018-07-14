package com.damiankwasniak.ryanair.feature.searchresult.ui

import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.ui.view.BaseView

interface SearchResultView: BaseView {

    fun showProgress(showProgress: Boolean)

    fun setToolbarTitle(originStationName: String, destinationStationName: String)

    fun setResult(availableFlightsList: List<FlightViewModel>, currentMaxPriceValue: Float)

    fun showNoResultView()

    fun setInitialValueOfRangebar(currentMaxPriceValue: Float)
}