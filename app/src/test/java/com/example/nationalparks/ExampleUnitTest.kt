package com.example.nationalparks

import android.content.Context
import android.content.SharedPreferences
import com.example.nationalparks.data.network.AuthApi
import com.example.nationalparks.data.repository.UserRepositoryImpl
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}