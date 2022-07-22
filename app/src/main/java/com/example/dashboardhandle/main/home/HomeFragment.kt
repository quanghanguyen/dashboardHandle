package com.example.dashboardhandle.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dashboardhandle.R
import com.example.dashboardhandle.`interface`.OnItemClickListerner
import com.example.dashboardhandle.databinding.FragmentHomeBinding
import com.example.dashboardhandle.main.home.homedetails.HomeDetailsActivity
import com.example.dashboardhandle.model.RequestModel
import com.example.dashboardhandle.user.UserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initEvents()
        initObserve()
        homeViewModel.load()
    }

    private fun initList() {
        homeBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            homeAdapter = HomeAdapter(arrayListOf())
            adapter = homeAdapter

            homeAdapter.setOnItemClickListerner(object :
            OnItemClickListerner {
                override fun onItemClick(requestData: RequestModel) {
                    HomeDetailsActivity.startDetails(context, requestData)
                    }
                }
            )
        }
    }

    private fun initObserve() {
        homeViewModel.loadList.observe(viewLifecycleOwner) {result ->
            when (result) {
                is HomeViewModel.LoadList.ResultOk -> {
                    homeAdapter.addNewData(result.list)
                }
                is HomeViewModel.LoadList.ResultError -> {
                    Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEvents() {
        homeBinding.userIcon.setOnClickListener {
            startActivity(Intent(context, UserActivity::class.java))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }
}