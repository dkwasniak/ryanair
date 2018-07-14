package com.damiankwasniak.ryanair.feature.stations.presenter

import com.damiankwasniak.ryanair.feature.stations.reducer.StationsReducer
import com.damiankwasniak.ryanair.feature.stations.reducer.StationsState
import com.damiankwasniak.ryanair.feature.stations.ui.StationsView
import com.damiankwasniak.ryanair.ui.presenter.BasePresenter
import com.damiankwasniak.ryanair.store
import tw.geothings.rekotlin.StoreSubscriber
import javax.inject.Inject

class StationsActivityPresenter @Inject constructor() : BasePresenter<StationsView>(), StoreSubscriber<StationsState> {

    override fun attached(view: StationsView) {
        super.attached(view)
        store.subscribe(this) {
            it.select {
                it.stationsState
            }
        }
    }

    override fun detached() {
        super.detached()
        store.unsubscribe(this)
    }

    override fun newState(state: StationsState) {

        if (StationsReducer.stationsFetched(state)) {
            view?.setResult(state.stationsList.stations)
        }
        view?.showProgress(StationsReducer.showProgress(state))
        if(StationsReducer.isError(state)) {
            view?.showError()
        }

    }
}