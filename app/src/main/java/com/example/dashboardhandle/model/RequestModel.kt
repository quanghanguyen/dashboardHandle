package com.example.dashboardhandle.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestModel(
    @SerializedName("userUid")
    val userUid : String = "",
    @SerializedName("matchId")
    val matchId: String = "",
    @SerializedName("deviceToken")
    val deviceToken : String = "",
    @SerializedName("name")
    val name : String = "",
    @SerializedName("datetime")
    val datetime : String = "",
    @SerializedName("pitch")
    val pitch : String = "",
    @SerializedName("people")
    val people : String = "",
    @SerializedName("note")
    val note : String = "",
    @SerializedName("click")
    var click : Int = 0,
    @SerializedName("clientUid1")
    var clientUid1 : String = "",
    @SerializedName("clientUid2")
    var clientUid2 : String = "",
    @SerializedName("clientUid3")
    var clientUid3 : String = ""
) : Parcelable