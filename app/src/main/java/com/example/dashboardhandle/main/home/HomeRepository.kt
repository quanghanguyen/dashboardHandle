package com.example.dashboardhandle.main.home

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
                    val list = ArrayList<RequestModel>()
                    for (requestSnapshot in snapshot.children) {
                        requestSnapshot.getValue(RequestModel::class.java)?.let {
                            list.add(0, it)
                        }
                    }
                    onSuccess(list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onFail(error.message)
            }
        })
    }
}