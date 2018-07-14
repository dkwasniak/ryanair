package com.damiankwasniak.ryanair.feature.searchresult.model

import com.damiankwasniak.emptyString
import com.google.gson.annotations.SerializedName

data class Fare(

        @SerializedName("type")
        var type: String = emptyString,

        @SerializedName("amount")
        var amount: Double = 0.1,

        @SerializedName("count")
        var count: Int = 0,

        @SerializedName("hasDiscount")
        var hasDiscount: Boolean = false,

        @SerializedName("publishedFare")
        var publishedFare: Double? = null,

        @SerializedName("discountInPercent")
        var discountInPercent: Int = 0,

        @SerializedName("hasPromoDiscount")
        var hasPromoDiscount: Boolean = false

)