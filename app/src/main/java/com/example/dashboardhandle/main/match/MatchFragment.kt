package com.example.dashboardhandle.main.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dashboardhandle.R
import com.example.dashboardhandle.databinding.FragmentMatchBinding

class MatchFragment : Fragment() {

    private lateinit var matchBinding: FragmentMatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        matchBinding = FragmentMatchBinding.inflate(inflater, container, false)
        return matchBinding.root
    }
}