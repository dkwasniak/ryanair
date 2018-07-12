package com.damiankwasniak.ryanair.feature.stations.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StationListModel(

    @SerializedName("stations")
    var stations: List<StationModel> = listOf()

) : Parcelable