package com.damiankwasniak.ryanair.feature.searchresult.presenter

import com.damiankwasniak.applySchedulers
import com.damiankwasniak.ryanair.di.SchedulersProvider
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.feature.searchresult.reducer.AvailableFlightReducer
import com.damiankwasniak.ryanair.feature.searchresult.reducer.AvailableFlightsState
import com.damiankwasniak.ryanair.feature.searchresult.ui.SearchResultView
import com.damiankwasniak.ryanair.store
import com.damiankwasniak.ryanair.ui.presenter.BasePresenter
import rx.Observable
import rx.lang.kotlin.subscribeBy
import tw.geothings.rekotlin.StoreSubscriber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchResultActivityPresenter @Inject constructor(
        private val schedulersProvider: SchedulersProvider
) : BasePresenter<SearchResultView>(), StoreSubscriber<AvailableFlightsState> {

    private var availableFlightsList: List<FlightViewModel> = emptyList()

    private var currentMaxPriceValue: Float = 150f

    override fun attached(view: SearchResultView) {
        super.attached(view)
        store.subscribe(this) {
            it.select { it.availableFlightsState }
        }
        view.setInitialValueOfRangebar(currentMaxPriceValue)
    }

    override fun detached() {
        super.detached()
        store.unsubscribe(this)
    }

    override fun newState(state: AvailableFlightsState) {
        view?.showProgress(AvailableFlightReducer.showProgress(state))

        if (AvailableFlightReducer.hasStationsDefined(state)) {
            view?.setToolbarTitle(state.originStationName!!, state.destinationStationName!!)
        }

        if (AvailableFlightReducer.flightsFetched(state)) {
            availableFlightsList = state.availableFlightsList
            if(availableFlightsList.isEmpty()) {
                view?.showNoResultView()
            } else {
                view?.setResult(state.availableFlightsList.filter { it.flightRegularFare <= currentMaxPriceValue }, currentMaxPriceValue)
            }
        }


        if(AvailableFlightReducer.isError(state)){
            view?.showError()
        }
    }

    fun onMaxPriceChanged(currentMaxPriceValue: Float) {
        Observable.just(currentMaxPriceValue)
                .debounce(800, TimeUnit.MILLISECONDS, schedulersProvider.ioScheduler)
                .compose(applySchedulers(schedulersProvider))
                .subscribeBy(
                        onNext = {
                            view?.setResult(availableFlightsList.filter { it.flightRegularFare <= currentMaxPriceValue }, it)
                        }
                )
    }
}