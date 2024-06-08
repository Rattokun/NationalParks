package com.example.nationalparks.domain.user

interface UserRepository {
    suspend fun getToken(): String

    suspend fun registerUser(username: String, password: String)
    suspend fun loginUser(username: String, password: String)
}
