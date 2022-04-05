package com.example.danteblessyou.newmodels.lastmatches

import com.google.gson.annotations.SerializedName

data class LastMatchesAwayTeamModel(
    @SerializedName("LastMatches") var awayLastMatchesList: ArrayList<LastMatchesListModel>? = null
)
