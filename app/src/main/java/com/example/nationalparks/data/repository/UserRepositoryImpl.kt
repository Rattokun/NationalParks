package com.example.nationalparks.data.repository

import ru.woyfit.domain.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
): UserRepository {

    override suspend fun getToken(): String {
        return ""
    }

    override suspend fun registerUser(username: String, password: String) {

    }

    override suspend fun loginUser(username: String, password: String) {

    }
}