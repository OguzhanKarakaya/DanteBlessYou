package com.example.danteblessyou.newmodels.header

import com.google.gson.annotations.SerializedName

data class HomeTeamModel(
    @SerializedName("Id") val id: Int? = 0,
    @SerializedName("NesineNameLong") val homeLongName: String? = ""
)