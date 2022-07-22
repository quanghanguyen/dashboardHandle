package com.example.dashboardhandle.firebaseconnection

import com.example.dashboardhandle.firebaseconnection.AuthConnection.uid
import com.example.dashboardhandle.request.RequestActivity
import com.example.dashboardhandle.request.RequestViewModel
import com.google.firebase.database.FirebaseDatabase

object DatabaseConnection {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    }