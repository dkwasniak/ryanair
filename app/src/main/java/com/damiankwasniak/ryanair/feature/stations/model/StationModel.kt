package com.damiankwasniak.ryanair.feature.stations.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StationModel(

        @SerializedName("code")
        var code: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("alternateName")
        var alternateName: String? = null,

        @SerializedName("alias")
        var alias: List<String>? = null,

        @SerializedName("countryCode")
        var countryCode: String? = null,

        @SerializedName("countryName")
        var countryName: String? = null,

        @SerializedName("countryGroupCode")
        var countryGroupCode: String? = null,

        @SerializedName("countryGroupName")
        var countryGroupName: String? = null,

        @SerializedName("timeZoneCode")
        var timeZoneCode: String? = null,

        @SerializedName("latitude")
        var latitude: String? = null,

        @SerializedName("longitude")
        var longitude: String? = null

) : Parcelable