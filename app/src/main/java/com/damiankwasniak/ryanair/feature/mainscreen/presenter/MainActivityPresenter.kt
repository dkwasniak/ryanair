package com.damiankwasniak.ryanair.feature.mainscreen.presenter

import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.feature.mainscreen.ui.MainView
import com.damiankwasniak.ryanair.feature.stations.dispatcher.StationsDispatcher
import com.damiankwasniak.ryanair.feature.stations.model.StationModel
import com.damiankwasniak.ryanair.feature.ui.presenter.BasePresenter
import java.util.*
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(private val stationsDispatcher: StationsDispatcher) : BasePresenter<MainView>() {


    private var originStationModel: StationModel? = null

    private var destinationStationModel: StationModel? = null

    private val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault())

    private val maxDate: Calendar = Calendar.getInstance(TimeZone.getDefault())

    private val minDate: Calendar = Calendar.getInstance(TimeZone.getDefault())

    private var adultsNumber = 0

    private var teensNumber = 0

    private var childrenNumber = 0

    var selectedDate: String = emptyString

    init {
        maxDate.add(Calendar.MONTH, 1)
        minDate.add(Calendar.DAY_OF_MONTH, 0)
    }

    fun fetchStations() {
        stationsDispatcher.fetchStations()
    }

    fun onOriginStationSelected(stationModel: StationModel?) {
        stationModel?.let {
            originStationModel = stationModel
            view?.setOriginStationName(stationModel.name)
        }
    }

    fun onDestinationStationSelected(stationModel: StationModel?) {
        stationModel?.let {
            destinationStationModel = stationModel
            view?.setDestinationStationName(stationModel.name)
        }
    }

    fun setDate(date: String?) {
        date?.let {
            selectedDate = date
            view?.setDate(date)
        }
    }

    fun initDatePicker() {
        view?.initDatePicker(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), maxDate.timeInMillis, minDate.timeInMillis)
    }

    fun onAdultsNumberChanged(value: Int) {
        adultsNumber = value
    }

    fun onTeensNumberChanged(value: Int) {
        teensNumber = value
    }

    fun onChildrenNumberChanged(value: Int) {
        childrenNumber = value
    }
}