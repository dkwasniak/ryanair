package com.damiankwasniak.ryanair.feature.searchresult.model

import com.damiankwasniak.emptyString
import com.google.gson.annotations.SerializedName

data class Segment(

        @SerializedName("segmentNr")
        var segmentNr: Int = 0,

        @SerializedName("origin")
        var origin: String = emptyString,

        @SerializedName("destination")
        var destination: String = emptyString,

        @SerializedName("flightNumber")
        var flightNumber: String = emptyString,

        @SerializedName("time")
        var time: List<String> = emptyList(),

        @SerializedName("timeUTC")
        var timeUTC: List<String> = emptyList(),

        @SerializedName("duration")
        var duration: String = emptyString

)