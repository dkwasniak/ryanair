package com.damiankwasniak.ryanair.feature.stations.model

import android.os.Parcelable
import com.damiankwasniak.emptyString
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StationModel(

        @SerializedName("code")
        var code: String? = emptyString,

        @SerializedName("name")
        var name: String? = emptyString,

        @SerializedName("alternateName")
        var alternateName: String? = emptyString,

        @SerializedName("alias")
        var alias: List<String> = emptyList(),

        @SerializedName("countryCode")
        var countryCode: String? = emptyString,

        @SerializedName("countryName")
        var countryName: String? = emptyString,

        @SerializedName("countryGroupCode")
        var countryGroupCode: String? = emptyString,

        @SerializedName("countryGroupName")
        var countryGroupName: String? = emptyString,

        @SerializedName("timeZoneCode")
        var timeZoneCode: String? = emptyString,

        @SerializedName("latitude")
        var latitude: String? = emptyString,

        @SerializedName("longitude")
        var longitude: String? = emptyString

) : Parcelable {

    fun isEmptyModel(): Boolean {
        return code.isNullOrEmpty()
    }
}