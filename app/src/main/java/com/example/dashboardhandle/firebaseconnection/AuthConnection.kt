package com.example.dashboardhandle.firebaseconnection

import com.google.firebase.auth.FirebaseAuth

object AuthConnection {
    val uid = FirebaseAuth.getInstance().currentUser?.uid
}