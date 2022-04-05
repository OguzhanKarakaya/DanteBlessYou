package com.example.danteblessyou.newmodels.lastmatches

import com.example.danteblessyou.newmodels.header.AwayTeamModel
import com.example.danteblessyou.newmodels.header.HomeTeamModel
import com.google.gson.annotations.SerializedName

data class LastMatchesListModel(
    @SerializedName("HomeTeam") var homeTeam: HomeTeamModel? = null,
    @SerializedName("AwayTeam") var awayTeam: AwayTeamModel? = null,
    @SerializedName("HTResult") var htResult: String? = "",
    @SerializedName("FTResult") var ftResult: String? = ""
)
