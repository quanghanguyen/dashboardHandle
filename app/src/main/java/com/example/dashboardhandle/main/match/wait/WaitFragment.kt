package com.example.dashboardhandle.main.match.wait

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dashboardhandle.R
import com.example.dashboardhandle.databinding.FragmentWaitBinding

class WaitFragment : Fragment() {

    private lateinit var waitBinding: FragmentWaitBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        waitBinding = FragmentWaitBinding.inflate(inflater, container, false)
        return waitBinding.root
    }
}