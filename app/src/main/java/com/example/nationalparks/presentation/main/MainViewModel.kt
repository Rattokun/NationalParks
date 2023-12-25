package com.example.nationalparks.presentation.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nationalparks.data.navigation.BottomNav
import com.example.nationalparks.data.navigation.MainDestinations
import com.example.nationalparks.data.repository.MainRepository
import com.example.nationalparks.data.repository.pojo.plants.PlantData
import com.example.nationalparks.data.repository.pojo.plants.PlantDataResponse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(savedStateHandle: SavedStateHandle,
    val mainRepository: MainRepository): ViewModel() {

    private val mCurrentScreen: MutableStateFlow<String> = MutableStateFlow(MainDestinations.HOME)

    private val mShouldShowCamera: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val shouldShowCamera: StateFlow<Boolean>
        get() = mShouldShowCamera
    val currentScreen: StateFlow<String>
        get() = mCurrentScreen
    val fPlants: MutableStateFlow<PlantData> = MutableStateFlow(PlantData(emptyList()))

    val listener = object : MainListener{
        override fun openCamera(isOpen: Boolean) {
            isOpenCamera(isOpen)
        }

        override fun onSearchClick() {
            viewModelScope.launch {
                val response = mainRepository.getPlants()
                response.body()?.let {
                    fPlants.value  = it
                }
                println(response)
            }
        }

        override fun onAddClick() {

        }

        override fun getSearchText(text: String) {

        }

        override fun onChangeScreen(route: BottomNav) {
            mCurrentScreen.value = route.route
        }
    }


    fun getPlants(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getPlants()
            response.body()?.let {
                fPlants.value = it
            }
        }
    }

    fun isOpenCamera(isOpen: Boolean){
        mShouldShowCamera.value = isOpen
    }
}