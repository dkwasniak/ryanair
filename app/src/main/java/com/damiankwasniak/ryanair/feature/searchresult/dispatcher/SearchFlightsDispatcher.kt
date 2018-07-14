package com.damiankwasniak.ryanair.feature.searchresult.dispatcher

import com.damiankwasniak.ryanair.feature.searchresult.action.FetchedAvailableFlights
import com.damiankwasniak.ryanair.feature.searchresult.data.FlightSearchService
import com.damiankwasniak.ryanair.feature.stations.model.StationModel
import com.damiankwasniak.ryanair.store
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchFlightsDispatcher @Inject constructor(
        private val flightSearchService: FlightSearchService
) {

    fun fetchAvailableFlights(origin: StationModel, dest: StationModel, dateOut: String, adults: Int, teen: Int, children: Int) {
        store.dispatch(FetchedAvailableFlights(origin.name, dest.name))
        flightSearchService.fetchedAvailableFlights(origin.code, dest.code, dateOut, adults, teen, children)
    }
}