package com.example.danteblessyou.newmodels.header

import com.google.gson.annotations.SerializedName

data class AwayTeamModel(
    @SerializedName("Id") val id: Int? = 0,
    @SerializedName("NesineNameLong") val awayLongName: String? = ""
)