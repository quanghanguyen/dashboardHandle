package com.example.dashboardhandle.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.dashboardhandle.R
import com.example.dashboardhandle.databinding.ActivityMainBinding
import com.example.dashboardhandle.main.home.HomeFragment
import com.example.dashboardhandle.main.match.MatchFragment
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(mainBinding.bottomNavigation, navController)

//        initBottomNavigation()

    }

//    private fun initBottomNavigation() {
//        mainBinding.bottomNavigation.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.home -> {
//                    loadFragment(HomeFragment())
//                    return@setOnNavigationItemSelectedListener
//                }
//                R.id.my_matches -> {
//                    loadFragment(MatchFragment())
//                    return@setOnNavigationItemSelectedListener
//                }
//            }
//        }
//    }

//    private fun loadFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
}