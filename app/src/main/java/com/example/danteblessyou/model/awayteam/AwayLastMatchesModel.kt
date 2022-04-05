package com.example.danteblessyou.model.awayteam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AwayLastMatchesModel(
    @SerializedName("Season") var season: String? = "",
    @SerializedName("League") val league: String? = "",
    @SerializedName("HTResult") val halfTimeResult: String? = "",
    @SerializedName("FTResult") val fullTimeResult: String? = "",
    @SerializedName("AwayTeam") val awayTeamDetail: AwayTeamDetailModel? = null,
    @SerializedName("SeasonId") val seasonId: Int? = 0
): Parcelable
