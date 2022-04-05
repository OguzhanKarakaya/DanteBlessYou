package com.example.danteblessyou.newmodels.leaguetable

import com.google.gson.annotations.SerializedName

data class LeagueTable(
    @SerializedName("LeagueTable") val leagueTableList: ArrayList<LeagueTableModel>? = null
)
