package com.example.danteblessyou.model.hometeam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeTeamDetailModel(
    @SerializedName("_id") var id: Int? = 0
): Parcelable
