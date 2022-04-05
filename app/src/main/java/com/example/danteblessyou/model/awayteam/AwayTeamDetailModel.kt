package com.example.danteblessyou.model.awayteam

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AwayTeamDetailModel(
    @SerializedName("_id") var id: Int? = 0
): Parcelable
