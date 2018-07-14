package com.damiankwasniak.ryanair.feature.detail.ui

import android.os.Bundle
import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.R.id.*
import com.damiankwasniak.ryanair.applicationComponent
import com.damiankwasniak.ryanair.feature.detail.presenter.FlightDetailActivityPresenter
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.ui.BaseActivity
import com.damiankwasniak.ryanair.ui.toolbar.ToolbarBackActionDecorator
import com.damiankwasniak.ryanair.ui.toolbar.ToolbarTitleDecorator
import com.nomtek.utils.visible
import kotlinx.android.synthetic.main.activity_flight_detail.*
import javax.inject.Inject

class FlightDetailActivity : BaseActivity(), FlightDetailView {

    companion object {

        private const val FLIGHT_MODEL_KEY = "flight_model_key"

        fun params(flightViewModel: FlightViewModel): Bundle {
            val bundle = Bundle()
            bundle.putParcelable(FLIGHT_MODEL_KEY, flightViewModel)
            return bundle
        }
    }

    @Inject
    lateinit var flightDetailActivityPresenter: FlightDetailActivityPresenter

    private var flightModel: FlightViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationComponent.inject(this)
        setContentView(R.layout.activity_flight_detail)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        flightDetailActivityPresenter.attached(this)
        flightModel = intent.getParcelableExtra(FLIGHT_MODEL_KEY)
        flightDetailActivityPresenter.setFlightViewModel(flightModel)
        setupToolbar()
    }

    private fun setupToolbar() {
        var title = emptyString
        flightModel?.let {
            title = String.format(getString(R.string.detail_screen_title), it.flightNumber)
        }
        toolbarController
                .addDecorator(ToolbarTitleDecorator(title))
                .addDecorator(ToolbarBackActionDecorator({
                    onBackPressed()
                }))
                .build(toolbar, R.layout.toolbar_flight_detail_screen)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        flightDetailActivityPresenter.detached()
    }

    override fun setFlightDate(flightDate: String) {
        flightDateLabel.text = String.format(getString(R.string.flight_date), flightDate)
    }

    override fun setRegularFare(flightRegularFare: Double, currency: String) {
        regularFareView.setPrice(flightRegularFare, currency)
    }

    override fun setBusinessFare(flightBusinessFare: Double, currency: String) {
        businessFareView.setPrice(flightBusinessFare, currency)
    }

    override fun setLeisureFare(flightLeisureFare: Double, currency: String) {
        leisureFareView.setPrice(flightLeisureFare, currency)
    }

    override fun noLeisureFareData() {
        leisureFareView.setPrice(noDataProvided = true)
    }

    override fun noBusinessFareData() {
        businessFareView.setPrice(noDataProvided = true)
    }

}
