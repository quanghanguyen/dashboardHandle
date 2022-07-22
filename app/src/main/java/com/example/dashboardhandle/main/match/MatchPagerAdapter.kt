package com.example.dashboardhandle.main.match

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dashboardhandle.main.match.upcoming.UpComingFragment
import com.example.dashboardhandle.main.match.wait.WaitFragment
import javax.inject.Inject

class MatchPagerAdapter @Inject constructor(fm : FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                UpComingFragment()
            }
            1 -> {
                WaitFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}