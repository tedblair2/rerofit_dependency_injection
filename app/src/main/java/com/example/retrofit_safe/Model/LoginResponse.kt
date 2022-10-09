package com.example.retrofit_safe.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class LoginResponse(
    val access_token: String,
    val token_type: String
)
