package com.damiankwasniak.ryanair.feature.searchresult.reducer

import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.feature.searchresult.action.AvailableFlightsFetched
import com.damiankwasniak.ryanair.feature.searchresult.action.AvailableFlightsFetchingError
import com.damiankwasniak.ryanair.feature.searchresult.action.FetchedAvailableFlights
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightViewModel
import org.junit.Assert
import org.junit.Test

class AvailableFlightsStateTest {

    @Test
    fun `origin and destination stations are set and list is fetching`() {
        //given
        val originName = "name"
        val destName = "name1"
        val action = FetchedAvailableFlights(originName, destName)

        //when
        val oldState = AvailableFlightsState(false, emptyList(), emptyString, emptyString, false)
        val newState = AvailableFlightReducer.reduce(action, oldState)
        val expectedState = AvailableFlightsState(true, emptyList(), originName, destName, false)

        //then
        Assert.assertEquals(newState, expectedState)
    }

    @Test
    fun `available flights fetched`() {
        //given
        val availableFlights: List<FlightViewModel> = listOf(FlightViewModel("1"), FlightViewModel("1"))
        val action = AvailableFlightsFetched(availableFlights)

        //when
        val oldState = AvailableFlightsState(true, emptyList(), emptyString, emptyString, false)
        val newState = AvailableFlightReducer.reduce(action, oldState)
        val expectedState = AvailableFlightsState(false, availableFlights, emptyString, emptyString, false)

        //then
        Assert.assertEquals(newState, expectedState)
    }

    @Test
    fun `flights fetching error`() {
        //given
        val action = AvailableFlightsFetchingError()

        //when
        val oldState = AvailableFlightsState(true, emptyList(), emptyString, emptyString, false)
        val newState = AvailableFlightReducer.reduce(action, oldState)
        val expectedState = AvailableFlightsState(false, emptyList(), emptyString, emptyString, true)

        //then
        Assert.assertEquals(newState, expectedState)
    }
}