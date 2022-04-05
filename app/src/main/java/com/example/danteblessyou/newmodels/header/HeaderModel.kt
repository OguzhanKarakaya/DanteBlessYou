package com.example.danteblessyou.newmodels.header

import com.google.gson.annotations.SerializedName

data class HeaderModel(
    @SerializedName("HomeTeam") val homeTeam: HomeTeamModel? = null,
    @SerializedName("AwayTeam") val awayTeam: AwayTeamModel? = null,
    @SerializedName("RoundDetail") val roundDetail: String? = "",
    @SerializedName("League") val league: LeagueModel? = null
)
