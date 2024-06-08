package com.example.nationalparks.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.nationalparks.data.network.request.AuthRequest
import com.example.nationalparks.data.network.response.AuthResponse

interface AuthApi {
    @POST("authapi/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>
}