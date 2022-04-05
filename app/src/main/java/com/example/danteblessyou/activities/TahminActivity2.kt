package com.example.danteblessyou.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.danteblessyou.R
import com.example.danteblessyou.adapters.TahminListAdapter
import com.example.danteblessyou.databinding.ActivityMain2Binding
import com.example.danteblessyou.model.DetailModel
import com.example.danteblessyou.model.FavoriteModel
import com.example.danteblessyou.model.MatchListModel
import com.example.danteblessyou.model.TestModel
import com.example.danteblessyou.model.awayteam.AwayLastMatchesModel
import com.example.danteblessyou.model.hometeam.HomeLastMatchesModel
import com.google.gson.Gson
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TahminActivity2 : AppCompatActivity() {

    private var queue: RequestQueue? = null
    private var detailModel: DetailModel? = null

    private var favoriteModel: FavoriteModel? = null
    private val favoriteModelList = ArrayList<FavoriteModel>()
    private val lastList: ArrayList<FavoriteModel>? = null

    private val testModelList: ArrayList<TestModel>? = null

    //private val adapter: TahminAdapter? = null
    private val recyclerView: RecyclerView? = null
    private val data = ""

    private val homeLastList = ArrayList<HomeLastMatchesModel>()
    private val awayLastList = ArrayList<AwayLastMatchesModel>()

    var homeAttigiGol = ArrayList<Int>()
    var homeYedigiGol = ArrayList<Int>()
    var awayAttigiGol = ArrayList<Int>()
    var awayYedigiGol = ArrayList<Int>()

    private var totalHomeGoal = 0
    private var totalHomeYedigiGoal = 0
    private var totalAwayGoal = 0
    private var totalAwayYedigiGoal = 0
    private var homeKG = 0
    private var awayKG = 0

    private var matchList = ArrayList<MatchListModel>()
    var formattedDate: String? = ""
    private var url = "https://istatistik.nesine.com/api/v2/code/lastmatches"

    private lateinit var binding : ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        val bundle: Bundle? = intent.extras
        if (bundle != null)
            matchList = bundle.get("matchList") as ArrayList<MatchListModel>

        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        formattedDate = df.format(c)

        queue = Volley.newRequestQueue(this)

        for (i in 283 until matchList.size) {
            if (matchList[i].date == formattedDate) {
                val detailUrl = url.replace("code", matchList[i].matchCode.toString())
                getMatchDetails(detailUrl, matchList[i].matchCode!!, "${matchList[i].day} - ${matchList[i].time}", i)
            }
        }
    }

    private fun getMatchDetails(detailUrl: String, mCode: Int, date: String, size: Int) {

        val request = StringRequest(Request.Method.GET, detailUrl, { response ->
            try {
                val jsonObject = JSONObject(response)
                val lastJsonObject = JSONObject(lastString(jsonObject))
                val dataObject = lastJsonObject.getJSONObject("Data")
                val detailObject = dataObject.getJSONObject("Detail")
                val gson = Gson()
                detailModel = gson.fromJson(detailObject.toString(), DetailModel::class.java)


                if (detailModel != null) {
                    val test = detailModel?.homeTeamModel?.homeLastMatchesList
                    val test2 = detailModel?.awayTeamModel?.awayLastMatchList
                    for (i in 0 until test!!.size) {
                        if (test.size > 2) {
                            if (test[i].seasonId != null) {
                                if (test[i].seasonId != 0) {
                                    if (test[i].seasonId == detailModel?.idModel?.currentMatchModel?.seasonId) {
                                        if (test[i].season != null
                                        ) if (test[i].season.equals("2021")
                                            || test[i].season.equals("21/22")
                                            || test[i].season.equals("2021/2022")
                                            || test[i].season.equals("2022")
                                        ) {
                                            if (detailModel?.homeTeamModel?.id === test[i].homeTeamDetail?.id) {
                                                homeLastList!!.add(test[i])
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                    for (i in 0 until test2!!.size) {
                        if (test2.size > 2) {
                            if (test2[i].seasonId != null) {
                                if (test2[i].seasonId != 0) {
                                    if (test2[i].seasonId === detailModel?.idModel?.currentMatchModel?.seasonId
                                    ) {
                                        if (test2[i].season != null
                                        ) if (test2[i].season.equals("2021")
                                            || test2[i].season.equals("21/22")
                                            || test2[i].season.equals("2021/2022")
                                            || test2[i].season.equals("2022")
                                        ) {
                                            if (detailModel?.awayTeamModel?.id === test2[i].awayTeamDetail?.id) {
                                                awayLastList!!.add(test2[i])
                                            }
                                        }
                                    }
                                }
                            } else {
                                Log.i("TAG", "getMatchDetails: ")
                            }
                        }


                    }
                }

                calculateHomeGoals()
                calculateAwayGoals()

                if (homeAttigiGol != null) {
                    if (homeAttigiGol.size > 0) {
                        for (i in homeAttigiGol.indices) {
                            totalHomeGoal = totalHomeGoal + homeAttigiGol[i]
                        }
                    }
                }

                if (homeYedigiGol != null) {
                    if (homeYedigiGol.size > 0) {
                        for (i in homeYedigiGol.indices) {
                            totalHomeYedigiGoal = totalHomeYedigiGoal + homeYedigiGol[i]
                        }
                    }
                }

                if (awayAttigiGol != null) {
                    if (awayAttigiGol.size > 0) {
                        for (i in awayAttigiGol.indices) {
                            totalAwayGoal = totalAwayGoal + awayAttigiGol[i]
                        }
                    }
                }

                if (awayYedigiGol != null) {
                    if (awayYedigiGol.size > 0) {
                        for (i in awayYedigiGol.indices) {
                            totalAwayYedigiGoal = totalAwayYedigiGoal + awayYedigiGol[i]
                        }
                    }
                }

                val evSahibiAttigiOrtalama =
                    totalHomeGoal.toString().toDouble() / homeAttigiGol.size.toString().toDouble()
                val evSahibiYedigiOrtalama =
                    totalHomeYedigiGoal.toString().toDouble() / homeYedigiGol.size.toString()
                        .toDouble()
                val depAttigiOrtalama =
                    totalAwayGoal.toString().toDouble() / awayAttigiGol.size.toString().toDouble()
                val depYedigiOrtalama =
                    totalAwayYedigiGoal.toString().toDouble() / awayYedigiGol.size.toString()
                        .toDouble()

                val evSahibiKG =
                    100 / (homeAttigiGol.size.toString().toDouble() / homeKG.toString().toDouble())
                val depKG =
                    100 / (awayAttigiGol.size.toString().toDouble() / awayKG.toString().toDouble())

                favoriteModel = FavoriteModel()
                favoriteModel?.evSahibi = detailModel?.homeTeamModel?.longName
                favoriteModel?.deplasman = detailModel?.awayTeamModel?.nameLong
                favoriteModel?.evKG = evSahibiKG
                favoriteModel?.depKG = depKG
                favoriteModel?.evAttigiGol = evSahibiAttigiOrtalama
                favoriteModel?.depAttigiGol = depAttigiOrtalama
                favoriteModel?.evYedigiGol = evSahibiYedigiOrtalama
                favoriteModel?.depYedigiGol = depYedigiOrtalama
                favoriteModel?.ortGol = (evSahibiAttigiOrtalama + evSahibiYedigiOrtalama + depAttigiOrtalama + depYedigiOrtalama) / 2
                favoriteModel?.ortKG = (evSahibiKG + depKG) / 2
                favoriteModel?.matchCode = mCode
                favoriteModel?.evAttigiTotal = totalHomeGoal
                favoriteModel?.evYedigiTotal = totalHomeYedigiGoal
                favoriteModel?.depAttigiTotal = totalAwayGoal
                favoriteModel?.depYedigiTotal = totalAwayYedigiGoal
                favoriteModel?.evToplamMac = homeYedigiGol.size
                favoriteModel?.depToplamMac = awayYedigiGol.size
                favoriteModel?.date = date

                favoriteModelList.add(favoriteModel!!)
                if (size == 300) {

                        val adapter = TahminListAdapter(favoriteModelList)
                        binding.recycler.layoutManager = LinearLayoutManager(this)
                        binding.recycler.adapter = adapter

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, { error ->
            Log.e("TAG", "downloadTask: $error")
        })
        queue?.add(request)
    }

    fun calculateHomeGoals() {
        homeKG = 0
        homeAttigiGol = ArrayList()
        homeYedigiGol = ArrayList()
        if (homeLastList != null) {
            if (homeLastList.size > 0) {
                for (i in 0 until homeLastList.size) {
                    if (homeLastList[i].fullTimeResult != null && !homeLastList[i].fullTimeResult?.contains("undefined")!!) {
                        val goals = homeLastList[i].fullTimeResult!!.split(":")
                        homeAttigiGol.add(goals[0].toInt())
                        homeYedigiGol.add(goals[1].toInt())
                        if (goals[0].toInt() > 0) {
                            if (goals[1].toInt() > 0) {
                                homeKG += 1
                            }
                        }
                    }
                }
            }
        }
    }

    fun calculateAwayGoals() {
        awayKG = 0
        awayAttigiGol = ArrayList()
        awayYedigiGol = ArrayList()
        if (awayLastList != null) {
            if (awayLastList.size > 0) {
                for (i in 0 until awayLastList.size) {
                    if (awayLastList[i].fullTimeResult != null && !awayLastList[i].fullTimeResult?.contains("undefined")!!) {
                        val goals = awayLastList[i].fullTimeResult!!.split(":")
                        awayAttigiGol.add(goals[1].toInt())
                        awayYedigiGol.add(goals[0].toInt())
                        if (goals[0].toInt() > 0) {
                            if (goals[1].toInt() > 0) {
                                awayKG += 1
                            }
                        }
                    }
                }
            }
        }
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