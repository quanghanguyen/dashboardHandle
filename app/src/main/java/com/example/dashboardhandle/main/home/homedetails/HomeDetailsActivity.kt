package com.example.dashboardhandle.main.home.homedetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dashboardhandle.R
import com.example.dashboardhandle.databinding.ActivityHomeDetailsBinding
import com.example.dashboardhandle.firebaseconnection.AuthConnection.uid
import com.example.dashboardhandle.model.RequestModel


class HomeDetailsActivity : AppCompatActivity() {

    private lateinit var homeDetailsBinding: ActivityHomeDetailsBinding

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
    }

    private fun initEvents() {
        binding()
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
                }
            }
        }
    }