package com.damiankwasniak.ryanair.feature.mainscreen

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.applicationComponent
import com.damiankwasniak.ryanair.feature.mainscreen.presenter.MainActivityPresenter
import com.damiankwasniak.ryanair.feature.mainscreen.ui.MainView
import com.damiankwasniak.ryanair.feature.stations.reducer.StationsReducer
import com.damiankwasniak.ryanair.feature.stations.ui.StationsListActivity
import com.damiankwasniak.ryanair.store
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView, DatePickerDialog.OnDateSetListener {


    companion object {
        const val ORIGIN_REQUEST_CODE = 1111

        const val DESTINATION_REQUEST_CODE = 1112
    }

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    private lateinit var dialog: DatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationComponent.inject(this)
        setContentView(R.layout.activity_main)
        initClickListeners()
        initSteppersListeners()
    }

    private fun initSteppersListeners() {
        numberOfAdultsStepper.onValueChanged = {
            mainActivityPresenter.onAdultsNumberChanged(it)
        }
        numberOfTeensStepper.onValueChanged = {
            mainActivityPresenter.onTeensNumberChanged(it)
        }
        numberOfChildrenStepper.onValueChanged = {
            mainActivityPresenter.onChildrenNumberChanged(it)
        }
    }

    override fun initDatePicker(year: Int, month: Int, day: Int, maxDate: Long, minDate: Long) {
        dialog = DatePickerDialog(this, this, year, month, day)
        dialog.datePicker.maxDate = maxDate
        dialog.datePicker.minDate = minDate

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val monthVal = month + 1
        val dayString = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
        val monthString = if (monthVal < 10) "0$monthVal" else "$monthVal"
        val date = "$year-$monthString-$dayString"
        mainActivityPresenter.setDate(date)
    }

    override fun setDate(date: String) {
        departureDateEditText.setText(date)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mainActivityPresenter.attached(this)
        mainActivityPresenter.initDatePicker()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mainActivityPresenter.detached()
    }

    private fun initClickListeners() {
        originStationEditText.setOnClickListener {
            startStationsActivity(ORIGIN_REQUEST_CODE)
        }

        destinationStationEditText.setOnClickListener {
            startStationsActivity(DESTINATION_REQUEST_CODE)
        }

        departureDateEditText.setOnClickListener {
            dialog.show()
        }
    }

    private fun startStationsActivity(code: Int) {
        if (!StationsReducer.stationsFetched(store.state.stationsState)) {
            mainActivityPresenter.fetchStations()
        }
        startActivityForResult(Intent(this, StationsListActivity::class.java), code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ORIGIN_REQUEST_CODE -> {
                    mainActivityPresenter.onOriginStationSelected(data?.getParcelableExtra(StationsListActivity.SELECTED_STATION))
                }
                DESTINATION_REQUEST_CODE -> {
                    mainActivityPresenter.onDestinationStationSelected(data?.getParcelableExtra(StationsListActivity.SELECTED_STATION))
                }
            }
        }
    }

    override fun showError(msg: Int) {

    }

    override fun setOriginStationName(name: String) {
        originStationEditText.setText(name)
    }

    override fun setDestinationStationName(name: String) {
        destinationStationEditText.setText(name)
    }

}
