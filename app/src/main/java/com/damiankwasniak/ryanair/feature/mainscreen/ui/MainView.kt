package com.damiankwasniak.ryanair.feature.mainscreen.ui

import com.damiankwasniak.ryanair.feature.ui.view.BaseView

interface MainView: BaseView {

    override fun showError(msg: Int)

    fun setOriginStationName(name: String)

    fun setDestinationStationName(name: String)

    fun initDatePicker(year: Int, month: Int, day: Int, maxDate: Long, minDate: Long)

    fun setDate(date: String)
}