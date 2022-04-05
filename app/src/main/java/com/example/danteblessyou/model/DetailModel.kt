package com.example.danteblessyou.model

import android.os.Parcelable
import com.example.danteblessyou.model.awayteam.AwayTeamModel
import com.example.danteblessyou.model.hometeam.HomeTeamModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailModel(
    @SerializedName("HomeTeam") var homeTeamModel: HomeTeamModel? = null,
    @SerializedName("AwayTeam") var awayTeamModel: AwayTeamModel? = null,
    @SerializedName("_id") var idModel: IDModel? = null
) : Parcelable
