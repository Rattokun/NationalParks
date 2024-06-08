package com.example.nationalparks.data.usecase

import com.example.nationalparks.domain.user.AuthUseCase
import com.example.nationalparks.domain.user.UserRepository

class AuthUseCaseImpl(
    private val userRepository: UserRepository
): AuthUseCase {
    override suspend fun registerUser(username: String, password: String) {
        userRepository.registerUser(username, password)
    }

    override suspend fun loginUser(username: String, password: String) {
        userRepository.loginUser(username, password)
    }

    override suspend fun getToken(): String = userRepository.getToken()
}