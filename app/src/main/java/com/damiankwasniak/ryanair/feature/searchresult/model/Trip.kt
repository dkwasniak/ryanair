package com.damiankwasniak.ryanair.feature.searchresult.model

import com.damiankwasniak.emptyString
import com.google.gson.annotations.SerializedName

data class Trip(

        @SerializedName("origin")
        var origin: String = emptyString,

        @SerializedName("originName")
        var originName: String = emptyString,

        @SerializedName("destination")
        var destination: String = emptyString,

        @SerializedName("destinationName")
        var destinationName: String = emptyString,

        @SerializedName("dates")
        var dates: List<DateModel> = emptyList()
)