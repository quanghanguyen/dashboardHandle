package com.example.dashboardhandle.request

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dashboardhandle.databinding.ActivityRequestBinding
import com.example.dashboardhandle.firebaseconnection.AuthConnection.uid
import com.example.dashboardhandle.firebaseconnection.DatabaseConnection
import com.example.dashboardhandle.firebaseconnection.MessageConnection
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestActivity : AppCompatActivity() {

    private lateinit var requestBinding: ActivityRequestBinding
    private val requestViewModel : RequestViewModel by viewModels()
    private var deviceToken : String? = null
    var name : String? = null
    var phone : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestBinding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(requestBinding.root)

        // Get Device Token
        MessageConnection.firebaseMessaging.token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                } else {
                    deviceToken = task.result
                }
            })

        // Get Name and Phone
        FirebaseDatabase.getInstance().getReference("Users").child(uid!!).get()
            .addOnSuccessListener {
                name = it.child("name").value.toString()
                phone = it.child("phone").value.toString()
            }

        initObserve()
        initEvents()
    }

    private fun initEvents() {
        requestBinding.send.setOnClickListener {
            with(requestBinding) {
                val datetime = date.text.toString()
                val pitch = pitch.text.toString()
                val people = people.text.toString()
                val note = note.text.toString()
                val matchId = DatabaseConnection.firebaseDatabase.getReference("Requests").push().key

                if (matchId != null) {
                    deviceToken?.let { it1 ->
                        if (uid != null) {
                            requestViewModel.sendRequest(userUid = uid, matchId = matchId, deviceToken = it1,
                                name = name!!, datetime = datetime, pitch = pitch, people = people, note = note
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initObserve() {
        requestViewModel.sendRequest.observe(this) {result ->
            when (result) {
                is RequestViewModel.SendRequest.ResultOk -> {
                    Toast.makeText(this, result.successMessage, Toast.LENGTH_SHORT).show()
                    finish()
                }
                is RequestViewModel.SendRequest.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}