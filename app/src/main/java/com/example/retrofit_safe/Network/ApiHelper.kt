package com.example.retrofit_safe.Network

import com.example.retrofit_safe.Model.LoginResponse
import com.example.retrofit_safe.Model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun login(username:String,password:String):Response<LoginResponse>
    suspend fun getUser(token:String):Response<User>
}