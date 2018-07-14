package com.damiankwasniak.ryanair.feature.stations.reducer

import com.damiankwasniak.ryanair.feature.stations.action.FetchStations
import com.damiankwasniak.ryanair.feature.stations.action.StationsFetched
import com.damiankwasniak.ryanair.feature.stations.action.StationsFetchingError
import com.damiankwasniak.ryanair.feature.stations.model.StationListModel
import org.junit.Assert
import org.junit.Test

class StationsStateTest {

    @Test
    fun `stations are fetching`() {
        //given
        val action = FetchStations()
        val stationsListModel = StationListModel()

        //when
        val oldState = StationsState(stationsListFetching = false, stationsList = stationsListModel)
        val newState = StationsReducer.reduce(action, oldState)
        val expectedState = StationsState(true, stationsListModel, false)

        //then
        Assert.assertEquals(newState, expectedState)
    }


    @Test
    fun `stations fetched`() {
        //given
        val stationsListModel = StationListModel()
        val action = StationsFetched(stationsListModel)

        //when
        val oldState = StationsState(stationsListFetching = true, stationsList = StationListModel())
        val newState = StationsReducer.reduce(action, oldState)
        val expectedState = StationsState(false, stationsListModel, false)

        //then
        Assert.assertEquals(newState, expectedState)
    }

    @Test
    fun `stations fetching error`() {
        //given
        val action = StationsFetchingError()

        //when
        val oldState = StationsState(stationsListFetching = true)
        val newState = StationsReducer.reduce(action, oldState)
        val expectedState = StationsState(false, oldState.stationsList, true)

        //then
        Assert.assertEquals(newState, expectedState)
    }
}