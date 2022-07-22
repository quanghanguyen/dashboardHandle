package com.example.dashboardhandle.user

import com.example.dashboardhandle.model.UserModel
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class UserRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {

    fun saveUser(
        userUid : String,
        name : String,
        phone : String,
        onSuccess : (String) -> Unit,
        onFail : (String) -> Unit
    ) {
        val userData = UserModel(userUid, name, phone)
        firebaseDatabase.getReference("Users").child(userUid).setValue(userData)
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