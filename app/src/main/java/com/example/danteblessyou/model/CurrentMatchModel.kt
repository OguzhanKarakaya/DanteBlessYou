package com.example.danteblessyou.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentMatchModel(
    @SerializedName("SeasonId") var seasonId: Int? = 0
):Parcelable
