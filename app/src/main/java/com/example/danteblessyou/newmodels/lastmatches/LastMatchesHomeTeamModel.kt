package com.example.danteblessyou.newmodels.lastmatches

import com.google.gson.annotations.SerializedName

data class LastMatchesHomeTeamModel(
    @SerializedName("LastMatches") var homeLastMatchesList: ArrayList<LastMatchesListModel>? = null
)
