package com.damiankwasniak.ryanair.feature.searchresult.model

import com.damiankwasniak.emptyString
import com.google.gson.annotations.SerializedName

data class FlightsList(

        @SerializedName("termsOfUse")
        var termsOfUse: String = emptyString,

        @SerializedName("currency")
        var currency: String = emptyString,

        @SerializedName("currPrecision")
        var currPrecision: Int = 0,

        @SerializedName("trips")
        var trips: List<Trip> = emptyList(),

        @SerializedName("serverTimeUTC")
        var serverTimeUTC: String = emptyString
)