package com.example.danteblessyou.newmodels

import com.example.danteblessyou.newmodels.header.HeaderModel
import com.example.danteblessyou.newmodels.header.LeagueModel
import com.example.danteblessyou.newmodels.lastmatches.LastMatchesModel
import com.example.danteblessyou.newmodels.leaguetable.LeagueTable
import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("Header") val headerModel: HeaderModel? = null,
    @SerializedName("LeagueTable") val leagueModel: LeagueTable? = null,
    @SerializedName("LastMatches") val lastMatches: LastMatchesModel? = null
)
