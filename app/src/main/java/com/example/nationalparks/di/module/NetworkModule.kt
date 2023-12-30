package com.example.nationalparks.di.module

import com.example.nationalparks.data.network.TrefleApi
import com.example.nationalparks.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providesBaseUrl() : String = "https://trefle.io/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl : String) : Retrofit {
        val httpHandler = HttpLoggingInterceptor()
        httpHandler.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(httpHandler)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit : Retrofit) : TrefleApi = retrofit.create(TrefleApi::class.java)

    @Provides
    @Singleton
    fun provideMainRemoteData(mainService : TrefleApi) : MainRepository = MainRepository(mainService)


}