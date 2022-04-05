package com.example.danteblessyou.newmodels.lastmatches

import com.google.gson.annotations.SerializedName

data class LastMatchesModel(
    @SerializedName("HomeTeam") val lastMatchHomeTeam: LastMatchesHomeTeamModel? = null,
    @SerializedName("AwayTeam") val lastMatchAwayTeam: LastMatchesAwayTeamModel? = null
)
