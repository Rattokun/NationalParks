package com.example.nationalparks.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.nationalparks.data.navigation.BottomNav
import com.example.nationalparks.data.navigation.MainDestinations
import com.example.nationalparks.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(savedStateHandle: SavedStateHandle,
    mainRepository: MainRepository): ViewModel() {

    private val mCurrentScreen: MutableStateFlow<String> = MutableStateFlow(MainDestinations.HOME)

    private val mShouldShowCamera: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val shouldShowCamera: StateFlow<Boolean>
        get() = mShouldShowCamera
    val currentScreen: StateFlow<String>
        get() = mCurrentScreen
    val listener = object : MainListener{
        override fun openCamera(isOpen: Boolean) {
            isOpenCamera(isOpen)
        }

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

    fun isOpenCamera(isOpen: Boolean){
        mShouldShowCamera.value = isOpen
    }


}