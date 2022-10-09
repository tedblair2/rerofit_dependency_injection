package com.example.retrofit_safe.Network

import com.example.retrofit_safe.Model.LoginResponse
import com.example.retrofit_safe.Model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("token")
    suspend fun login(
        @Field("username") username:String,
        @Field("password") email:String):Response<LoginResponse>

    @GET("users/me")
    suspend fun getUser(@Header("Authorization") token:String):Response<User>
}