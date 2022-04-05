package com.example.danteblessyou.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteModel(
    @SerializedName("evKG") var evKG: Double? = 0.0,
    @SerializedName("depKG") var depKG: Double? = 0.0,
    @SerializedName("evAttigiGol") var evAttigiGol: Double? = 0.0,
    @SerializedName("evYedigiGol") var evYedigiGol: Double? = 0.0,
    @SerializedName("depAttigiGol") var depAttigiGol: Double? = 0.0,
    @SerializedName("depYedigiGol") var depYedigiGol: Double? = 0.0,
    @SerializedName("ortKG") var ortKG: Double? = 0.0,
    @SerializedName("ortGol") var ortGol: Double? = 0.0,

    @SerializedName("evSahibi") var evSahibi: String? = "",
    @SerializedName("deplasman") var deplasman: String? = "",
    @SerializedName("date") var date: String? = "",

    @SerializedName("depAttigiTotal") var depAttigiTotal: Int? = 0,
    @SerializedName("depYedigiTotal") var depYedigiTotal: Int? = 0,
    @SerializedName("evAttigiTotal") var evAttigiTotal: Int? = 0,
    @SerializedName("evYedigiTotal") var evYedigiTotal: Int? = 0,
    @SerializedName("matchCode") var matchCode: Int? = 0,
    @SerializedName("evToplamMac") var evToplamMac: Int? = 0,
    @SerializedName("depToplamMac") var depToplamMac: Int? = 0
): Parcelable {
    fun testInt(test: Int): String {
        return test.toString()
    }

    fun testDouble(test: Double): String {
        return test.toString()
    }
}