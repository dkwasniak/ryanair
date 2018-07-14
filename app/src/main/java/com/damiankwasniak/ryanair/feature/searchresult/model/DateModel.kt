package com.damiankwasniak.ryanair.feature.searchresult.model

import com.damiankwasniak.emptyString
import com.google.gson.annotations.SerializedName

data class DateModel(

        @SerializedName("dateOut")
        var dateOut: String = emptyString,

        @SerializedName("flights")
        var flights: List<FlightModel> = emptyList()

)