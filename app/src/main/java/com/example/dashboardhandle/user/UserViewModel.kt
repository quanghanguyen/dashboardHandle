package com.example.dashboardhandle.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){

    val saveResult = MutableLiveData<SaveResult>()

    sealed class SaveResult {
        class ResultOk(val successMessage : String): SaveResult()
        class ResultError(val errorMessage : String) : SaveResult()
    }

    fun save(userUid : String, name : String, phone : String) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            userRepository.saveUser(userUid, name, phone, onSuccess = {
                saveResult.value = SaveResult.ResultOk("Success")
            }, onFail = {
                saveResult.value = SaveResult.ResultOk(it)
            })
        }
    }
}