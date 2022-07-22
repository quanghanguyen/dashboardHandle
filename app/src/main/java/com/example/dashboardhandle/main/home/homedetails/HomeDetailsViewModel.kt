package com.example.dashboardhandle.main.home.homedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailsViewModel @Inject constructor(private val homeDetailsRepository: HomeDetailsRepository) : ViewModel(){

    val updateRequest = MutableLiveData<UpdateRequest>()

    sealed class UpdateRequest {
        class ResultOk(val successMessage : String) : UpdateRequest()
        class ResultError(val errorMessage : String) : UpdateRequest()
    }

    fun handleUpdate(
        matchId : String,
        uid : String,
        clientUid : String,
        click : Int,
    ) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            homeDetailsRepository.update(matchId, uid, clientUid, click, {
                updateRequest.value = UpdateRequest.ResultOk("Success")
            }, {
                updateRequest.value = UpdateRequest.ResultError(it)
            })
        }
    }

}