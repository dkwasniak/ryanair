package com.damiankwasniak.ryanair.feature.searchresult.model

import com.damiankwasniak.emptyString
import com.google.gson.annotations.SerializedName

class FlightModel(

        @SerializedName("faresLeft")
        var faresLeft: Int = 0,

        @SerializedName("flightKey")
        var flightKey: String = emptyString,

        @SerializedName("infantsLeft")
        var infantsLeft: Int = 0,

        @SerializedName("regularFare")
        var regularFare: RegularFare = RegularFare(),

        @SerializedName("segments")
        var segments: List<Segment> = emptyList(),

        @SerializedName("flightNumber")
        var flightNumber: String = emptyString,

        @SerializedName("time")
        var time: List<String>? = null,

        @SerializedName("timeUTC")
        var timeUTC: List<String> = emptyList(),

        @SerializedName("duration")
        var duration: String = emptyString

)