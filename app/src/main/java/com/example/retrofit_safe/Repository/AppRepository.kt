package com.example.retrofit_safe.Repository

import androidx.lifecycle.MutableLiveData
import com.example.retrofit_safe.Model.LoginResponse
import com.example.retrofit_safe.Model.User
import com.example.retrofit_safe.Network.ApiHelper
import com.example.retrofit_safe.Util.Resource
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiHelper: ApiHelper){

    val response_ = MutableLiveData<Resource<LoginResponse>>()
    val user= MutableLiveData<Resource<User>>()


    suspend fun login(username:String,password:String){
        val response=apiHelper.login(username,password)
        response_.postValue(Resource.loading(null))
        if (response.isSuccessful){
            response_.postValue(Resource.success(response.body()))
        }else{
            try {
                val error=response.errorBody()?.string()
                val msg=JSONObject(error).getString("detail")
                response_.postValue(Resource.error(null,msg))
            }catch (e:JSONException){}
        }
    }
    suspend fun getUser(token:String){
        val response=apiHelper.getUser(token)
        user.postValue(Resource.loading(null))
        if (response.isSuccessful){
            user.postValue(Resource.success(response.body()))
        }else{
            try {
                val error=response.errorBody()?.string()
                val msg=JSONObject(error).getString("detail")
                user.postValue(Resource.error(null,msg))
            }catch (e:JSONException){}
        }
    }

}