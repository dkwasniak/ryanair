package com.damiankwasniak.ryanair.reducer

import com.damiankwasniak.ryanair.feature.searchresult.reducer.AvailableFlightReducer
import com.damiankwasniak.ryanair.feature.searchresult.reducer.AvailableFlightsState
import com.damiankwasniak.ryanair.feature.stations.reducer.StationsReducer
import com.damiankwasniak.ryanair.feature.stations.reducer.StationsState
import tw.geothings.rekotlin.Action
import tw.geothings.rekotlin.StateType

/**
 * Created by damiankwasniak on 09.04.2018.
 */

class AppState(
        var stationsState: StationsState = StationsState(),
        var availableFlightsState: AvailableFlightsState = AvailableFlightsState()
) : StateType

object AppReducer {

    fun appReducer(action: Action, appState: AppState?): AppState {
        return AppState(
                stationsState = StationsReducer.reduce(action, appState?.stationsState),
                availableFlightsState = AvailableFlightReducer.reduce(action, appState?.availableFlightsState)
        )
    }
}


