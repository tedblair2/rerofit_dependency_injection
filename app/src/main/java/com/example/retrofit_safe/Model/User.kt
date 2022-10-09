package com.example.retrofit_safe.Model

data class User(
    val disabled: Boolean,
    val email: String,
    val full_name: String,
    val hashed_password: String,
    val username: String
)
