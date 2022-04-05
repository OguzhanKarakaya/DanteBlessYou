package com.example.danteblessyou.model.awayteam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AwayTeamModel(
    @SerializedName("_id") var id: Int? = 0,
    @SerializedName("NesineNameLong") val nameLong: String? = "",
    @SerializedName("NesineNameShort") val nameShort: String? = "",
    @SerializedName("IsHomeTeam") val isHomeTeam: Boolean = false,
    @SerializedName("IsAwayTeam") val isAwayTeam: Boolean = false,
    @SerializedName("LastMatches") val awayLastMatchList: ArrayList<AwayLastMatchesModel>? = null
): Parcelable
