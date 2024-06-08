package com.example.nationalparks.data.repository

import android.content.SharedPreferences
import com.example.nationalparks.data.local.BaseSharedPref
import com.example.nationalparks.data.network.AuthApi
import com.example.nationalparks.data.network.request.AuthRequest
import com.example.nationalparks.data.network.response.AuthResponse
import ru.woyfit.data.network.request.LoginRequest
import ru.woyfit.data.network.request.SignupRequest
import com.example.nationalparks.domain.user.UserRepository
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val sharedPref: SharedPreferences

){
    fun getUserName(id: Int): String = when(id){
        0 -> "Ahui"
        1 -> "Kim Chen"
        else -> "None"
    }

    suspend open fun requestContent(): String {
        delay(1000L)
        return "Content"
    }

    suspend fun login(name: String, password: String): Response<AuthResponse> {
        val response = authApi.login(AuthRequest(name, password))
        response.body()?.let {
            println(it.token)
            sharedPref.edit().putString(BaseSharedPref.TOKEN_PREF, it.token)
        }
        return response
    }

    suspend fun getToken(): String{
        return sharedPref.getString(BaseSharedPref.TOKEN_PREF, "token")!!
    }
}