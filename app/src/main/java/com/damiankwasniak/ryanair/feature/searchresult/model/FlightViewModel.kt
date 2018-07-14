package com.damiankwasniak.ryanair.feature.searchresult.model

import android.os.Parcelable
import com.damiankwasniak.emptyString
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightViewModel(

        val flightDate: String = emptyString,

        val flightNumber: String = emptyString,

        val flightDuration: String = emptyString,

        val flightRegularFare: Double = 0.0,

        val flightBusinessFare: Double = 0.0,

        val flightLeisureFare: Double = 0.0,

        val currency: String = emptyString
) : Parcelable