package com.example.nationalparks.presentation.main

import com.example.nationalparks.data.navigation.BottomNav

interface BottomBarListener {
    fun onChangeScreen(route: BottomNav)
}