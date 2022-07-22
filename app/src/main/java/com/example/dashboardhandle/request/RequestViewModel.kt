package com.example.dashboardhandle.request

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboardhandle.user.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.thread

@HiltViewModel
class RequestViewModel @Inject constructor(private val requestRepository : RequestRepository) : ViewModel() {

    val sendRequest = MutableLiveData<SendRequest>()

    sealed class SendRequest {
        class ResultOk(val successMessage : String): SendRequest()
        class ResultError(val errorMessage : String) : SendRequest()
    }

    fun sendRequest(
        userUid : String,
        matchId : String,
        deviceToken : String,
        name : String,
        datetime : String,
        pitch : String,
        people : String,
        note: String
    ) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            requestRepository.sendRequest(userUid, matchId, deviceToken, name, datetime, pitch, people, note, {
                sendRequest.value = SendRequest.ResultOk("Success")
            }, {
                sendRequest.value = SendRequest.ResultError(it)
            })
        }
    }
}