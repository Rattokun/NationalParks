package com.example.nationalparks.presentation.main

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.nationalparks.data.navigation.BottomNav
import com.example.nationalparks.data.navigation.MainDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel() {

    private val mCurrentScreen: MutableStateFlow<String> = MutableStateFlow(MainDestinations.HOME)
    val currentScreen: StateFlow<String>
        get() = mCurrentScreen
    val listener = object : MainListener{
        override fun onSearchClick() {

        }

        override fun onAddClick() {

        }

        override fun getSearchText(text: String) {

        }

        override fun onChangeScreen(route: BottomNav) {
            mCurrentScreen.value = route.route
        }
    }
}