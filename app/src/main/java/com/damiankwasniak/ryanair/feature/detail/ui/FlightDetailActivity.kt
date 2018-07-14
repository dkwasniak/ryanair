package com.damiankwasniak.ryanair.feature.detail.ui

import android.os.Bundle
import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.applicationComponent
import com.damiankwasniak.ryanair.feature.detail.presenter.FlightDetailActivityPresenter
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.ui.BaseActivity
import com.damiankwasniak.ryanair.ui.toolbar.ToolbarBackActionDecorator
import com.damiankwasniak.ryanair.ui.toolbar.ToolbarTitleDecorator
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

    override fun bindTripDate(flightModel: FlightViewModel) {
        with(flightModel) {
            setFlightDate(flightDate)
            setRegularFare(flightRegularFare, currency)
            setFlightOrigin(origin)
            setFlightDestination(destination)
            setFlightInfantsLeft(infantsLeft)
            setFlightFareClass(fareClass)
            setFlightDiscount(discountPercent)
        }

    }

    private fun setFlightDiscount(discountPercent: Int) {
        discountTextView.text = String.format(this.getString(R.string.discount), discountPercent)
    }

    private fun setFlightFareClass(fareClass: String) {
        fareClassTextView.text = String.format(this.getString(R.string.fare_class), fareClass)
    }

    private fun setFlightInfantsLeft(infantsLeft: Int) {
        infantsLeftTextView.text = String.format(this.getString(R.string.infants_left), infantsLeft)
    }

    private fun setFlightDestination(destination: String) {
        destinationTextView.text = String.format(this.getString(R.string.destination), destination)
    }

    private fun setFlightOrigin(origin: String) {
        originTextView.text = String.format(this.getString(R.string.origin), origin)
    }

    private fun setFlightDate(flightDate: String) {
        flightDateLabel.text = String.format(getString(R.string.flight_date), flightDate)
    }

    private fun setRegularFare(flightRegularFare: Double, currency: String) {
        regularFareView.text = String.format(this.getString(R.string.price), flightRegularFare, currency)
    }

}
