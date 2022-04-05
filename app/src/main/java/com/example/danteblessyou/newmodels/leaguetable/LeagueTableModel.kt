package com.example.danteblessyou.newmodels.leaguetable

import com.google.gson.annotations.SerializedName

data class LeagueTableModel(
    @SerializedName("TeamId") val teamId: Int? = 0,
    @SerializedName("PositionHome") val positionHome: Int? = 0,
    @SerializedName("PositionAway") val positionAway: Int? = 0,
    @SerializedName("MatchesHome") val matchesHome: Int? = 0,
    @SerializedName("MatchesAway") val matchesAway: Int? = 0,
    @SerializedName("AverageHome") val averageHomeGoal: String? = "",
    @SerializedName("AverageAway") val averageAwayGoal: String? = "",
    @SerializedName("WinningRatioHome") val homeWinRatio: Double? = 0.0,
    @SerializedName("WinningRatioAway") val awayWinRatio: Double? = 0.0
)

/*
          "MatchesTotal": 21,
          "MatchesAway": 10,
          "MatchesHome": 11,

          "WinTotal": 8,
          "WinAway": 4,
          "WinHome": 4,

          "DrawTotal": 5,
          "DrawAway": 2,
          "DrawHome": 3,

          "LossTotal": 8,
          "LossAway": 4,
          "LossHome": 4,

          "AverageTotal": "28:23",
          "AverageAway": "9:10",
          "AverageHome": "19:13",

          "PointsTotal": 29,
          "PointsAway": 14,
          "PointsHome": 15,

          "WinningRatioTotal": 38.095238095238095,
          "WinningRatioHome": 36.36363636363637,
          "WinningRatioAway": 40,

 */
