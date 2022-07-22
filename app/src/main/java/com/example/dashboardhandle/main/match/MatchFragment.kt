package com.example.dashboardhandle.main.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dashboardhandle.R
import com.example.dashboardhandle.databinding.FragmentMatchBinding
import com.google.android.material.tabs.TabLayoutMediator

class MatchFragment : Fragment() {

    private lateinit var matchBinding: FragmentMatchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val matchPagerAdapter = MatchPagerAdapter(childFragmentManager, lifecycle)
        matchBinding.viewPager.adapter = matchPagerAdapter

        TabLayoutMediator(matchBinding.tabLayout, matchBinding.viewPager) {tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Trận sắp đá"
                }
                1 -> {
                    tab.text = "Đã gửi yêu cầu"
                }
            }
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        matchBinding = FragmentMatchBinding.inflate(inflater, container, false)
        return matchBinding.root
    }
}