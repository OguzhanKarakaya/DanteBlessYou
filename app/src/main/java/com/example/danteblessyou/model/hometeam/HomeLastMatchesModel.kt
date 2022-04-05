package com.example.danteblessyou.model.hometeam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeLastMatchesModel(
    @SerializedName("Season") var season: String? = "",
    @SerializedName("League") val league: String? = "",
    @SerializedName("HTResult") val halfTimeResult: String? = "",
    @SerializedName("FTResult") val fullTimeResult: String? = "",
    @SerializedName("HomeTeam") val homeTeamDetail: HomeTeamDetailModel? = null,
    @SerializedName("SeasonId") val seasonId: Int? = 0
) : Parcelable
