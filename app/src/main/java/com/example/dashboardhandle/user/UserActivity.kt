package com.example.dashboardhandle.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dashboardhandle.R
import com.example.dashboardhandle.databinding.ActivityUserBinding
import com.example.dashboardhandle.databinding.FragmentMatchBinding
import com.example.dashboardhandle.firebaseconnection.AuthConnection.uid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var userBinding: ActivityUserBinding
    private val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(userBinding.root)
        initObserve()
        intiEvent()
    }

    private fun intiEvent() {
        save()
    }

    private fun save() {
        userBinding.save.setOnClickListener {
            with (userBinding) {
                val name = name.text.toString()
                val phone = phone.text.toString()

                if (uid != null) {
                    userViewModel.save(userUid = uid, name = name, phone = phone)
                }
            }
            finish()
        }
    }

    private fun initObserve() {
        userViewModel.saveResult.observe(this) {result ->
            when (result) {
                is UserViewModel.SaveResult.ResultOk -> {
                    Toast.makeText(this, result.successMessage, Toast.LENGTH_SHORT).show()
                    finish()
                }
                is UserViewModel.SaveResult.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}