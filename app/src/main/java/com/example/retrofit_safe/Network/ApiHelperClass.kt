package com.example.retrofit_safe.Network

import com.example.retrofit_safe.Model.LoginResponse
import com.example.retrofit_safe.Model.User
import retrofit2.Response
import javax.inject.Inject

class ApiHelperClass @Inject constructor(private val apiService: ApiService):ApiHelper {
    override suspend fun login(username: String, password: String): Response<LoginResponse> {
        return apiService.login(username,password)
    }

    override suspend fun getUser(token: String): Response<User> {
        return apiService.getUser(token)
    }
}