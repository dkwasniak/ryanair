package com.damiankwasniak.ryanair.feature.stations.ui

import com.damiankwasniak.ryanair.feature.stations.model.StationModel
import com.damiankwasniak.ryanair.ui.view.BaseView

interface StationsView : BaseView{

    fun showProgress(showProgress: Boolean)

    fun setResult(stations: List<StationModel>)
}