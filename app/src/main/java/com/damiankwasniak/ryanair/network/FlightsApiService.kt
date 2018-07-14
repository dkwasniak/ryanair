package com.damiankwasniak.ryanair.network

import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.feature.searchresult.model.FlightsList
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface FlightsApiService {

    @GET("Availability")
    fun searchFlights(@Query("origin") origin: String? = emptyString,
                      @Query("destination") dest: String? = emptyString,
                      @Query("dateout") dateOut: String,
                      @Query("adt") adults: Int,
                      @Query("teen") teen: Int,
                      @Query("chd") children: Int,
                      @Query("roundtrip") roundTrip: Boolean = false): Observable<FlightsList>

}