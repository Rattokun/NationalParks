package ru.woyfit.domain.user

interface AuthUseCase {
    suspend fun registerUser(username: String, password: String)
    suspend fun loginUser(username: String, password: String)
    suspend fun getToken():String
}