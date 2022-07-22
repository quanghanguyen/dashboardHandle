package com.example.dashboardhandle.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel (
    @SerializedName("userUid")
    val userUid : String = "",
    @SerializedName("name")
    val name : String = "",
    @SerializedName("phone")
    val phone : String = ""
) : Parcelable