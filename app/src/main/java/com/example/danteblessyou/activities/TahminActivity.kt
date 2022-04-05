package com.example.danteblessyou.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.danteblessyou.R
import com.example.danteblessyou.databinding.ActivityTahminBinding
import com.example.danteblessyou.model.DetailModel
import com.example.danteblessyou.model.FavoriteModel
import com.example.danteblessyou.model.MatchListModel
import com.example.danteblessyou.newmodels.DataModel
import com.example.danteblessyou.newmodels.PredictionModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log


class TahminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTahminBinding
    private var matchList = ArrayList<MatchListModel>()
    private var detailModel = DetailModel()
    private var favoriteModelList = ArrayList<FavoriteModel>()
    private var url = "https://istatistik.nesine.com/api/v2/718660/summary"
    private var dataModel = DataModel()
    private var predictionModel = PredictionModel()
    private var predictionList = ArrayList<PredictionModel>()

    private var homeTotalScore: String? = ""
    private var homeTotalConceded: String? = ""
    private var awayTotalScore: String? = ""
    private var awayTotalConceded: String? = ""
    private var homeTotalMatchCount: Int? = 0
    private var awayTotalMatchCount: Int? = 0
    private var homeWinRatio: Double? = 0.0
    private var awayWinRatio: Double? = 0.0

    private var queue: RequestQueue? = null
    var formattedDate: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tahmin)

        val bundle: Bundle? = intent.extras
        if (bundle != null)
            matchList = bundle.get("matchList") as ArrayList<MatchListModel>

        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        formattedDate = df.format(c)

        queue = Volley.newRequestQueue(this)

        for (i in 0 until matchList.size) {
            val detailUrl = url.replace("code", matchList[i].matchCode.toString())
            getMatchDetails(detailUrl)
        }
    }

    private fun getMatchDetails(detailUrl: String) {
        val request = StringRequest(Request.Method.GET, detailUrl, { response ->
            try {
                val jsonObject = JSONObject(response)
                val lastJsonObject = JSONObject(lastString(jsonObject))
                val dataObject = lastJsonObject.getJSONObject("Data")
                val groupListType: Type = object : TypeToken<DataModel>() {}.type
                val gson = Gson()
                dataModel = gson.fromJson(dataObject.toString(), groupListType)

                homeTeamGoals()
                awayTeamGoals()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, { error ->
            Log.e("TAG", "downloadTask: $error")
        })
        queue?.add(request)
    }

    private fun calculateKG() {
        val homeTeamList = dataModel.lastMatches?.lastMatchHomeTeam?.homeLastMatchesList
        var homeTeamKG: Int = 0
        if (homeTeamList != null) {
            for (i in homeTeamList) {
                if (i.homeTeam?.id == dataModel.headerModel?.homeTeam?.id) {
                    val result = i.ftResult?.split(":")
                    if (result?.get(0) != "0") {
                        if (result?.get(1) != "0")
                            homeTeamKG += 1
                    }
                }
            }
        }

    }

    private fun homeTeamGoals() {
        predictionModel = PredictionModel()
        var homeTeamKG: Int = 0
        val lTableList = dataModel.leagueModel?.leagueTableList
        if (lTableList != null) {
            for (i in 0 until lTableList.size) {
                if (lTableList[i].teamId == dataModel.headerModel?.homeTeam?.id) {
                    val test = lTableList[i].averageHomeGoal?.split(":")
                    predictionModel.homeTotalScore = test?.get(0)
                    predictionModel.homeTotalConceded = test?.get(1)

                    predictionModel.homeTotalMatchCount = lTableList[i].matchesHome
                    predictionModel.homeWinRatio =
                        String.format("%.2f", lTableList[i].homeWinRatio).toDouble()
                    predictionModel.homeTeamName = dataModel.headerModel?.homeTeam?.homeLongName


                    predictionModel.homeAverageScore = String.format(
                        "%.2f",
                        predictionModel.homeTotalMatchCount?.let {
                            test?.get(0)?.toDouble()?.div(it)
                        }).toDouble()

                    predictionModel.homeAverageConceded = String.format(
                        "%.2f",
                        predictionModel.homeTotalMatchCount?.let {
                            test?.get(1)?.toDouble()?.div(it)
                        }).toDouble()
                }
            }
        }
    }

    private fun awayTeamGoals() {
        val lTableList = dataModel.leagueModel?.leagueTableList
        if (lTableList != null) {
            for (i in 0 until lTableList!!.size) {
                if (lTableList[i].teamId == dataModel.headerModel?.awayTeam?.id) {
                    val test = lTableList[i].averageAwayGoal?.split(":")
                    predictionModel.awayTotalScore = test?.get(1)
                    predictionModel.awayTotalConceded = test?.get(0)
                    predictionModel.awayTotalMatchCount = lTableList[i].matchesAway
                    predictionModel.awayWinRatio =
                        String.format("%.2f", lTableList[i].awayWinRatio).toDouble()
                    predictionModel.awayTeamName = dataModel.headerModel?.awayTeam?.awayLongName
                    predictionModel.matchDate = formattedDate


                    predictionModel.awayAverageScore = String.format("%.2f",
                        predictionModel.awayTotalMatchCount?.let {
                            test?.get(1)?.toDouble()?.div(it)
                        }).toDouble()

                    predictionModel.awayAverageConceded = String.format("%.2f",
                        predictionModel.awayTotalMatchCount?.let {
                            test?.get(0)?.toDouble()?.div(it)
                        }).toDouble()

                    val totalAvgScrHome = predictionModel.homeAverageConceded?.let {
                        predictionModel.homeAverageScore?.plus(
                            it
                        )?.div(2)
                    }

                    val totalAvgScrAway =
                        (predictionModel.awayAverageScore?.plus(predictionModel.awayAverageConceded!!)
                            ?.toDouble()
                            ?.div(2))


                    predictionModel.totalAverageScore =
                        (totalAvgScrAway?.let { totalAvgScrHome?.plus(it) })
                }
            }
        }
        //if (predictionModel.totalAverageScore!! > 3)
        setPredictionList(predictionModel)
    }

    private fun setPredictionList(preModel: PredictionModel) {
        predictionList.add(preModel)
    }

    private fun lastString(jsonObject: JSONObject): String {
        val b = jsonObject.toString().substring(1, jsonObject.toString().length - 1)
        var a: String? = method(b)
        a = a?.let { StringBuilder(a).insert(it.length, "}").toString() }
        a = StringBuilder(a).insert(0, "{").toString()
        var newWord = a.replace("\\", "")
        newWord = "{\"Data\":" + newWord.substring(9)
        return newWord
    }

    private fun method(str: String?): String? {
        var str = str
        if (str != null && str.isNotEmpty()) {
            str = str.substring(0, str.length - 1)
        }
        return str
    }
}