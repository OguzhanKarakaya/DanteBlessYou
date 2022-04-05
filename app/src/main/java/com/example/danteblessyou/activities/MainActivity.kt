package com.example.danteblessyou.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.danteblessyou.R
import com.example.danteblessyou.adapters.MatchListAdapter
import com.example.danteblessyou.databinding.ActivityMainBinding
import com.example.danteblessyou.model.MatchListModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val url = "https://bulten.nesine.com/api/bulten/getprebultendelta"

    private var matchList = ArrayList<MatchListModel>()
    private var lastMatchList = ArrayList<MatchListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //downloadTask()

        binding.btnTahmin.setOnClickListener { onTahminClicked() }
        binding.btnFavori.setOnClickListener {
            /*val intent = Intent(this, TahminActivity2::class.java)
            intent.putExtra("matchList", lastMatchList)
            startActivity(intent)*/
            getJson()
        }
    }

    private fun onTahminClicked() {
        val intent = Intent(this, TahminActivity::class.java)
        intent.putExtra("matchList", lastMatchList)
        startActivity(intent)
    }

    private fun getJson() {
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, { response ->

            try {
                val jsonObject = JSONObject(response)
                val sgJsonObject = jsonObject.getJSONObject("sg")
                val eaJsonArray = sgJsonObject.getJSONArray("EA")

                val groupListType: Type = object : TypeToken<ArrayList<MatchListModel?>?>() {}.type
                val gson = Gson()
                matchList = gson.fromJson(eaJsonArray.toString(), groupListType)

                for (i in 0 until matchList.size) {
                    if (matchList[i].awayTeam != null) {
                        if (matchList[i].type == 1) lastMatchList.add(matchList[i])
                    }
                }

                val adapter = MatchListAdapter(lastMatchList)
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = adapter

            } catch (e: Exception) {
                e.printStackTrace()
            }



        }, { error ->
            Log.i("error", "json alınamadı $error")
        })
        queue.add(request)
    }

    private fun downloadTask() {
        binding.progressBar.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, { response ->

            try {
                val jsonObject = JSONObject(response)
                val sgObject: JSONObject = jsonObject.getJSONObject("sg")
                val eaArray: JSONArray = sgObject.getJSONArray("EA")
                val groupListType: Type = object : TypeToken<ArrayList<MatchListModel?>?>() {}.type
                val gson = Gson()
                matchList = gson.fromJson(eaArray.toString(), groupListType)

                for (i in 0 until matchList.size) {
                    if (matchList[i].awayTeam != null) {
                        if (matchList[i].type == 1) lastMatchList.add(matchList[i])
                    }
                }

                val adapter = MatchListAdapter(lastMatchList)
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = adapter

                binding.progressBar.visibility = View.GONE

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }, { error ->
            Log.e("TAG", "downloadTask: $error")
        })

        queue.add(request)
    }
}