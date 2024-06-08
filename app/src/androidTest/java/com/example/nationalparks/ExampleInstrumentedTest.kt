package com.example.nationalparks

import android.content.Context
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nationalparks.data.local.BaseSharedPref
import com.example.nationalparks.data.network.AuthApi
import com.example.nationalparks.data.repository.UserRepositoryImpl
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://trefle.io/")
        .build()
    val fakeApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    private val pref = InstrumentationRegistry.getInstrumentation().targetContext.getSharedPreferences("base_prefs", Context.MODE_PRIVATE)

    private val repo: UserRepositoryImpl = UserRepositoryImpl(authApi = fakeApi, pref)
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.nationalparks", appContext.packageName)
    }

    @Test
    fun useContext() {
        assertEquals("Ahui", repo.getUserName(0))
    }

    @Test
    fun getPref() = runBlocking {
        pref.edit().putString(BaseSharedPref.TOKEN_PREF, "token").apply()
        delay(1000)
        assertEquals("token", pref.getString(BaseSharedPref.TOKEN_PREF, null))
    }

    @Test
    fun assertCoroutineContext() = runBlocking {
        val expectedResult = "Content"
        assertEquals(expectedResult, repo.requestContent())
    }
}