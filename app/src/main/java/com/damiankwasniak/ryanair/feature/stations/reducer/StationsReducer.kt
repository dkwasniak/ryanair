package com.damiankwasniak.ryanair.feature.stations.reducer

import com.damiankwasniak.ryanair.feature.stations.action.FetchStations
import com.damiankwasniak.ryanair.feature.stations.action.StationsFetched
import com.damiankwasniak.ryanair.feature.stations.action.StationsFetchingError
import com.damiankwasniak.ryanair.feature.stations.model.StationListModel
import tw.geothings.rekotlin.Action


data class StationsState(
        val stationsListFetching: Boolean = false,
        val stationsList: StationListModel = StationListModel(),
        val isError: Boolean = true
)

object StationsReducer {

    fun reduce(action: Action, stationsState: StationsState?): StationsState {
        var state = stationsState ?: StationsState()
        when (action) {
            is FetchStations -> {
                state = state.copy(stationsListFetching = true, stationsList = StationListModel(), isError = false)
            }
            is StationsFetched -> {
                state = state.copy(stationsListFetching = false, stationsList = action.stationsList, isError = false)
            }
            is StationsFetchingError -> {
                state = state.copy(stationsListFetching = false, isError = true)
            }
        }
        return state
    }

    fun stationsFetched(stationsState: StationsState): Boolean {
        return !stationsState.stationsListFetching && !stationsState.stationsList.stations.isEmpty()
    }

    fun showProgress(state: StationsState): Boolean {
        return state.stationsListFetching
    }

    fun isError(state: StationsState): Boolean {
        return state.isError
    }

}