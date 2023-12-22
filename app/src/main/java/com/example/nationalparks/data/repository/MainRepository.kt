package com.example.nationalparks.data.repository

import com.example.nationalparks.data.network.TrefleApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(val trefleApi: TrefleApi) {

}