package com.example.danteblessyou.newmodels


data class PredictionModel(
    var homeTeamName: String? = "",
    var awayTeamName: String? = "",
    var matchDate: String? = "",
    var homeTotalScore: String? = "",
    var homeTotalConceded: String? = "",
    var awayTotalScore: String? = "",
    var awayTotalConceded: String? = "",
    var homeTotalMatchCount: Int? = 0,
    var awayTotalMatchCount: Int? = 0,
    var homeWinRatio: Double? = 0.0,
    var awayWinRatio: Double? = 0.0,
    var homeAverageScore: Double? = 0.0,
    var homeAverageConceded: Double? = 0.0,
    var awayAverageScore: Double? = 0.0,
    var awayAverageConceded: Double? = 0.0,
    var homeKG: Double? = 0.0,
    var awayKG: Double? = 0.0,
    var totalAverageScore: Double? = 0.0,
    var totalKG: Double? = 0.0
)