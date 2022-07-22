package com.example.dashboardhandle.main.match.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dashboardhandle.R
import com.example.dashboardhandle.databinding.FragmentUpComingBinding

class UpComingFragment : Fragment() {

    private lateinit var upComingBinding: FragmentUpComingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        upComingBinding = FragmentUpComingBinding.inflate(inflater, container, false)
        return upComingBinding.root
    }
}