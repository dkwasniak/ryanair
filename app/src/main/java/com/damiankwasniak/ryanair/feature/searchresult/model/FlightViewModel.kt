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

        val currency: String = emptyString,

        val origin: String = emptyString,

        val destination: String = emptyString,

        val infantsLeft: Int = 0,

        val fareClass: String = emptyString,

        val discountPercent: Int = 0
) : Parcelable