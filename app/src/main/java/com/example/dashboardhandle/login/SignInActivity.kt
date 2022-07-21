package com.example.dashboardhandle.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dashboardhandle.main.MainActivity
import com.example.dashboardhandle.databinding.ActivitySignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private lateinit var signInBinding: ActivitySignInBinding
    private val signInViewModel : SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInBinding.root)

        initEvents()
        initObserve()
    }
    private fun initObserve() {
        signInViewModel.signInResult.observe(this) {result ->
            when (result) {
                is SignInViewModel.SignInResult.ResultOk -> {
                    Toast.makeText(this, result.successMessage, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is SignInViewModel.SignInResult.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEvents() {
        signInBinding.login.setOnClickListener {
            val email = signInBinding.email.text.toString()
            val password = signInBinding.password.text.toString()
            signInViewModel.signIn(email, password)
        }
    }
}