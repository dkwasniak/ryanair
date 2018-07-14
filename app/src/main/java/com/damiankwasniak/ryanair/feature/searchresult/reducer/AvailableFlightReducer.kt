package com.damiankwasniak.ryanair.feature.searchresult.reducer

import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.feature.searchresult.action.AvailableFlightsFetched
import com.damiankwasniak.ryanair.feature.searchresult.action.AvailableFlightsFetchingError
import com.damiankwasniak.ryanair.feature.searchresult.action.FetchedAvailableFlights
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import com.damiankwasniak.ryanair.feature.stations.reducer.StationsState
import tw.geothings.rekotlin.Action

data class AvailableFlightsState(
        val isFetching: Boolean = false,
        val availableFlightsList: List<FlightViewModel> = emptyList(),
        val originStationName: String? = emptyString,
        val destinationStationName: String? = emptyString,
        val isError: Boolean = false
)

object AvailableFlightReducer {

    fun reduce(action: Action, availableState: AvailableFlightsState?): AvailableFlightsState {
        var state = availableState ?: AvailableFlightsState()
        when (action) {
            is FetchedAvailableFlights -> {
                state = state.copy(isFetching = true, availableFlightsList = emptyList(), isError = false,
                        originStationName = action.originStationName, destinationStationName = action.destinationStationName)
            }
            is AvailableFlightsFetched -> {
                state = state.copy(isFetching = false, availableFlightsList = action.flightsList, isError = false)
            }
            is AvailableFlightsFetchingError -> {
                state = state.copy(isFetching = false, isError = true)
            }
        }
        return state
    }

    fun hasStationsDefined(state: AvailableFlightsState): Boolean {
        return !state.originStationName.isNullOrEmpty() && !state.destinationStationName.isNullOrEmpty()
    }

    fun flightsFetched(state: AvailableFlightsState): Boolean {
        return !state.isFetching && !state.isError
    }

    fun showProgress(state: AvailableFlightsState): Boolean {
        return state.isFetching
    }

    fun isError(state: AvailableFlightsState): Boolean {
        return state.isError
    }
}