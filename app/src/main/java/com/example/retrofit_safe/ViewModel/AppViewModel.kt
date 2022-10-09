package com.example.retrofit_safe.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_safe.Model.LoginResponse
import com.example.retrofit_safe.Model.User
import com.example.retrofit_safe.Repository.AppRepository
import com.example.retrofit_safe.Util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor (private val repository: AppRepository):ViewModel() {

    private val response=repository.response_
    private val user=repository.user
    val liveResponse:LiveData<Resource<LoginResponse>> =response
    val liveUser:LiveData<Resource<User>> = user

    fun login(username:String,password:String)=viewModelScope.launch {
        repository.login(username, password)
    }

    fun getUser(token:String)=viewModelScope.launch {
        repository.getUser(token)
    }
}