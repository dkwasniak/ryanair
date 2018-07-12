package com.damiankwasniak.ryanair.feature.stations.model

import com.google.gson.annotations.SerializedName

data class Market(

        @SerializedName("code")
        var code: String? = null,

        @SerializedName("group")
        var group: Any? = null

)