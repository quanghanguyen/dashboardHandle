package com.example.dashboardhandle.main.home.homedetails

import com.example.dashboardhandle.firebaseconnection.DatabaseConnection
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class HomeDetailsRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {
    fun update(
        matchId : String,
        uid : String,
        clientUid: String,
        click : Int,
        onSuccess : (String) -> Unit,
        onFail : (String) -> Unit
    ) {
        val user = mapOf(
            clientUid to uid,
            "click" to click
        )

        DatabaseConnection.firebaseDatabase.getReference("Requests").child(matchId).updateChildren(user)
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