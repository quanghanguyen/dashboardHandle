package com.example.dashboardhandle.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboardhandle.model.RequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel(){

    val loadList = MutableLiveData<LoadList>()

    sealed class LoadList {
        class ResultOk(val list : ArrayList<RequestModel>) : LoadList()
        class ResultError(val errorMessage: String) : LoadList()
    }

    fun load() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            homeRepository.loadList({
                loadList.value = LoadList.ResultOk(it)
            }, {
                loadList.value = LoadList.ResultError(it)
            })
        }
    }

}