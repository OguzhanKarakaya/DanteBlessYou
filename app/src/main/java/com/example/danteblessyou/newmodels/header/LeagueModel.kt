package com.example.danteblessyou.newmodels.header

import com.google.gson.annotations.SerializedName

data class LeagueModel(
    @SerializedName("LeagueName") val leagueName: String? = "",
    @SerializedName("LeagueAbbreviation") val leagueAbbreviation: String? = ""
) {
    fun getLeagueInfos(): String {
        return "$leagueAbbreviation - $leagueName"
    }
}