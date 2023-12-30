package com.example.nationalparks.data.repository

import com.example.nationalparks.data.network.TrefleApi
import com.example.nationalparks.data.repository.pojo.plants.PlantData
import com.example.nationalparks.data.repository.pojo.plants.PlantDataResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(val trefleApi: TrefleApi) {

    suspend fun getPlants(): Response<PlantData> {
        return trefleApi.getPlants(5, "_nWoT7ek3RMYpCvaSLfrE3S1BWXblk1df3IeA2ww064")
    }
}