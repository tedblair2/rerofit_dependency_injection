package com.example.retrofit_safe.Module

import com.example.retrofit_safe.Network.ApiHelper
import com.example.retrofit_safe.Network.ApiHelperClass
import com.example.retrofit_safe.Network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit()=Retrofit.Builder()
        .baseUrl("https://fastapi-retrofit.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit)=retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelperclass(apiHelperClass: ApiHelperClass):ApiHelper =apiHelperClass
}