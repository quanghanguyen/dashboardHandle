package com.example.dashboardhandle.main.home.homedetails

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dashboardhandle.R
import com.example.dashboardhandle.databinding.ActivityHomeDetailsBinding
import com.example.dashboardhandle.firebaseconnection.AuthConnection.uid
import com.example.dashboardhandle.firebaseconnection.MessageConnection
import com.example.dashboardhandle.model.RequestModel
import com.google.android.gms.tasks.OnCompleteListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailsActivity : AppCompatActivity() {

    private lateinit var homeDetailsBinding: ActivityHomeDetailsBinding
    private val homeDetailsViewModel : HomeDetailsViewModel by viewModels()
    private var matchId : String? = null

    companion object {
        private const val KEY_DATA = "request_data"
        fun startDetails(context: Context, data : RequestModel?)
        {
            context.startActivity(Intent(context, HomeDetailsActivity::class.java).apply {
                putExtra(KEY_DATA, data)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeDetailsBinding = ActivityHomeDetailsBinding.inflate(layoutInflater)
        setContentView(homeDetailsBinding.root)

        initEvents()
        initObserve()
    }

    private fun initObserve() {
        homeDetailsViewModel.updateRequest.observe(this) {result ->
            when (result) {
                is HomeDetailsViewModel.UpdateRequest.ResultOk -> {
                    Toast.makeText(this, result.successMessage, Toast.LENGTH_SHORT).show()
                    finish()
                }
                is HomeDetailsViewModel.UpdateRequest.ResultError -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEvents() {
        binding()
        updateRequest()
    }

    private fun updateRequest() {
        var click = homeDetailsBinding.click.text.toString().toInt()
        homeDetailsBinding.sendRequest.setOnClickListener {
            click += 1
            val clientUid = "clientUid$click"
            matchId?.let { matchId ->
                if (uid != null) {
                    homeDetailsViewModel.handleUpdate(matchId, uid, clientUid, click)
                }
            }
        }
    }

    private fun binding() {
        intent?.let { bundle ->
            val data = bundle.getParcelableExtra<RequestModel>(KEY_DATA)
            with(homeDetailsBinding) {
                if (data?.userUid == uid) {
                    sendRequest.visibility = View.GONE
                }
                name.text = data?.name
                pitch.text = data?.pitch
                note.text = data?.note
                datetime.text = data?.datetime
                click.text = data?.click.toString()
                matchId = data?.matchId
                }
            }
        }
    }