package com.example.danteblessyou.model.hometeam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeTeamModel(
    @SerializedName("_id") var id: Int? = 0,
    @SerializedName("NesineNameLong") var longName: String? = "",
    @SerializedName("NesineNameShort") var shortName: String? = "",
    @SerializedName("IsHomeTeam") var isHomeTeam: Boolean? = false,
    @SerializedName("IsAwayTeam") var isAwayTeam: Boolean? = false,
    @SerializedName("LastMatches") var homeLastMatchesList: ArrayList<HomeLastMatchesModel>
) : Parcelable
