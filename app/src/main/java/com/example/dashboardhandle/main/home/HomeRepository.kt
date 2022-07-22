package com.example.dashboardhandle.main.home

import android.util.Log
import android.widget.Toast
import com.example.dashboardhandle.firebaseconnection.AuthConnection.uid
import com.example.dashboardhandle.model.RequestModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class HomeRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {
    fun loadList(
        onSuccess : (ArrayList<RequestModel>) -> Unit,
        onFail : (String) -> Unit
    ) {
        firebaseDatabase.getReference("Requests").addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val listRequest = ArrayList<RequestModel>()
                    for (requestSnapshot in snapshot.children) {
                        requestSnapshot.getValue(RequestModel::class.java)?.let {list ->
                            when {
                                uid != list.clientUid1 && uid != list.clientUid2 && uid != list.clientUid3 -> {
                                    listRequest.add(0, list)
                                }
                                else -> {
                                    Log.e("Thong bao", "Failed")
                                }
                            }
                        }
                    }
                    onSuccess(listRequest)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onFail(error.message)
            }
        })
    }
}