package com.damiankwasniak.ryanair.feature.stations.dispatcher

import com.damiankwasniak.ryanair.feature.stations.action.FetchStations
import com.damiankwasniak.ryanair.feature.stations.data.StationsService
import com.damiankwasniak.ryanair.store
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationsDispatcher @Inject constructor(
        private val stationsService: StationsService
) {

    fun fetchStations() {
        store.dispatch(FetchStations())
        stationsService.fetchStations()
    }
}