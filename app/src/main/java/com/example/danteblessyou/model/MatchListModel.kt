package com.example.danteblessyou.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchListModel(
    @SerializedName("C") val matchCode: Int? = 0,
    @SerializedName("HN") val homeTeam: String? = "",
    @SerializedName("AN") val awayTeam: String? = "",
    @SerializedName("D") val date: String? = "",
    @SerializedName("DAY") val day: String? = "",
    @SerializedName("T") val time: String? = "",
    @SerializedName("TYPE") val type: Int? = 0
) : Parcelable {

    fun setHomeAndAway(): String {
        return "$homeTeam - $awayTeam"
    }

    fun setExactTime(): String {
        return "$day - $time"
    }
}
