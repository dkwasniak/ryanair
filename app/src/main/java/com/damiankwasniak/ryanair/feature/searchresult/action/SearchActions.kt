package com.damiankwasniak.ryanair.feature.searchresult.action

import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightsList
import tw.geothings.rekotlin.Action

class FetchedAvailableFlights(val originStationName: String?, val destinationStationName: String?) : Action

class AvailableFlightsFetched(val flightsList: List<FlightViewModel>) : Action

class AvailableFlightsFetchingError : Action