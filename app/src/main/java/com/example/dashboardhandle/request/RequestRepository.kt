package com.example.dashboardhandle.request

import com.example.dashboardhandle.model.RequestModel
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class RequestRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase){

    fun sendRequest(
        userUid : String,
        matchId : String,
        deviceToken : String,
        name : String,
        datetime : String,
        pitch : String,
        people : String,
        note: String,
        onSuccess : (String) -> Unit,
        onFail : (String) -> Unit
    ) {
        val requestData = RequestModel(userUid, matchId, deviceToken, name, datetime, pitch, people, note)
        firebaseDatabase.getReference("Requests").child(matchId).setValue(requestData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSuccess(it.toString())
                } else {
                    onFail(it.exception?.message.orEmpty())
                }
            }
            .addOnFailureListener {
                onFail(it.message.orEmpty())
            }
        }
    }