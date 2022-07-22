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
    val note : String = ""
) : Parcelable