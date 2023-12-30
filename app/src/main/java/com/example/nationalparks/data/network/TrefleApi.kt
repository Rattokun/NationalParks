package com.example.nationalparks.data.network

import com.example.nationalparks.data.repository.pojo.plants.PlantData
import com.example.nationalparks.data.repository.pojo.plants.PlantDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrefleApi {
    @GET("api/v1/plants")
    suspend fun getPlantsStr(@Query(value = "limit") limit: Int,
                          @Query(value = "token", encoded = true) token: String):String
    @GET("api/v1/plants")
    suspend fun getPlants(@Query(value = "limit") limit: Int,
                          @Query(value = "token", encoded = true) token: String): Response<PlantData>
}