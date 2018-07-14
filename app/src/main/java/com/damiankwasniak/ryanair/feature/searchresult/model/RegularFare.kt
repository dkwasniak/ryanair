package com.damiankwasniak.ryanair.feature.searchresult.model

import com.damiankwasniak.emptyString
import com.google.gson.annotations.SerializedName

data class RegularFare(

        @SerializedName("fareKey")
        var fareKey: String = emptyString,

        @SerializedName("fareClass")
        var fareClass: String = emptyString,

        @SerializedName("fares")
        var fares: List<Fare> = emptyList()

)