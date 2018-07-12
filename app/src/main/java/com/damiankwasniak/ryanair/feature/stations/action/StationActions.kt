package com.damiankwasniak.ryanair.feature.stations.action

import com.damiankwasniak.ryanair.feature.stations.model.StationListModel
import tw.geothings.rekotlin.Action

class FetchStations : Action

class StationsFetched(val stationsList: StationListModel) : Action

class StationsFetchingError : Action