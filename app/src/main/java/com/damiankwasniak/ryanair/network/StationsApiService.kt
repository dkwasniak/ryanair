package com.damiankwasniak.ryanair.network

import com.damiankwasniak.ryanair.feature.stations.model.StationListModel
import retrofit2.http.GET
import rx.Observable

interface StationsApiService {

    @GET("stations.json")
    fun fetchStations(): Observable<StationListModel>

}