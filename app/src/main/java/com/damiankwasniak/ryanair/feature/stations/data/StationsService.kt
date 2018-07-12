package com.damiankwasniak.ryanair.feature.stations.data

import com.damiankwasniak.applySchedulers
import com.damiankwasniak.ryanair.di.SchedulersProvider
import com.damiankwasniak.ryanair.feature.stations.action.StationsFetched
import com.damiankwasniak.ryanair.feature.stations.action.StationsFetchingError
import com.damiankwasniak.ryanair.network.StationsApiService
import com.damiankwasniak.ryanair.store
import rx.lang.kotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationsService @Inject constructor(
        private val stationsApiService: StationsApiService,
        private val schedulersProvider: SchedulersProvider
) {

    fun fetchStations() {
        stationsApiService.fetchStations()
                .compose(applySchedulers(schedulersProvider))
                .subscribeBy(
                        onNext = {
                            store.dispatch(StationsFetched(it))
                        },
                        onError = {
                           store.dispatch(StationsFetchingError())
                        }
                )

    }


}