package com.damiankwasniak.ryanair.feature.searchresult.ui

import android.os.Bundle
import com.appyvet.materialrangebar.RangeBar
import com.damiankwasniak.emptyString
import com.damiankwasniak.goToActivity
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.applicationComponent
import com.damiankwasniak.ryanair.di.SchedulersProvider
import com.damiankwasniak.ryanair.feature.detail.ui.FlightDetailActivity
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.feature.searchresult.presenter.SearchResultActivityPresenter
import com.damiankwasniak.ryanair.ui.BaseActivity
import com.damiankwasniak.ryanair.ui.toolbar.ToolbarBackActionDecorator
import com.damiankwasniak.ryanair.ui.toolbar.ToolbarTitleDecorator
import com.damiankwasniak.setViewVisibility
import com.damiankwasniak.showToast
import com.nomtek.utils.gone
import com.nomtek.utils.visible
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.toolbar_search_result_screen.*
import javax.inject.Inject


class SearchResultActivity : BaseActivity(), SearchResultView {

    @Inject
    lateinit var searchResultActivityPresenter: SearchResultActivityPresenter

    private val rangeBarChangeListener: RangeBar.OnRangeBarChangeListener = RangeBar.OnRangeBarChangeListener { _, _, _, _, rightPinValue ->
        filterLabel.text = String.format(getString(R.string.current_maximum_price), rightPinValue)
        searchResultActivityPresenter.onMaxPriceChanged(rightPinValue.toFloat())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationComponent.inject(this)
        setContentView(R.layout.activity_search_result)
        searchResultRecyclerView.onTripSelected = {
            showTripDetail(it)
        }
        setupToolbar()
    }

    override fun setInitialValueOfRangebar(currentMaxPriceValue: Float) {
        rangebar.setOnRangeBarChangeListener(null)
        rangebar.setRangePinsByValue(0f, currentMaxPriceValue)
        rangebar.setOnRangeBarChangeListener(rangeBarChangeListener)
    }

    private fun setupToolbar() {
        toolbarController
                .addDecorator(ToolbarTitleDecorator(emptyString))
                .addDecorator(ToolbarBackActionDecorator({
                    onBackPressed()
                }))
                .build(toolbar, R.layout.toolbar_search_result_screen)
    }

    private fun showTripDetail(flightViewModel: FlightViewModel) {
        goToActivity(FlightDetailActivity::class.java, FlightDetailActivity.params(flightViewModel))
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        searchResultActivityPresenter.attached(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        searchResultActivityPresenter.detached()
    }

    override fun showProgress(showProgress: Boolean) {
        filterGroup.setViewVisibility(!showProgress)
        searchResultRecyclerView.setViewVisibility(!showProgress)
        progressBar.setViewVisibility(showProgress)
    }

    override fun setResult(availableFlightsList: List<FlightViewModel>, currentMaxPriceValue: Float) {
        if (availableFlightsList.isNotEmpty()) {
            noResultTextView.gone()
            searchResultRecyclerView.visible()
            filterLabel.text = String.format(getString(R.string.current_maximum_price), currentMaxPriceValue.toString())
            searchResultRecyclerView.items = availableFlightsList
        } else {
            noResultTextView.visible()
            searchResultRecyclerView.gone()
        }
    }

    override fun setToolbarTitle(originStationName: String, destinationStationName: String) {
        titleTextView.text = String.format(getString(R.string.search_result_screen_title), originStationName, destinationStationName)
    }

    override fun showNoResultView() {
        noResultTextView.visible()
        filterGroup.gone()
        searchResultRecyclerView.gone()
        progressBar.gone()
    }

    override fun showError(error: String) {
        if(!error.isNotEmpty()) {
            showToast(error)
        } else {
            showToast(getString(R.string.general_critical_error))
        }

    }
}
