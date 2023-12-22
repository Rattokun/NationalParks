package com.example.nationalparks.di.module

import com.example.nationalparks.data.network.TrefleApi
import com.example.nationalparks.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesBaseUrl() : String = "https://example-hilt-retrofit-default-rtdb.firebaseio.com/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideMainService(retrofit : Retrofit) : TrefleApi = retrofit.create(TrefleApi::class.java)

    @Provides
    @Singleton
    fun provideMainRemoteData(mainService : TrefleApi) : MainRepository = MainRepository(mainService)
}