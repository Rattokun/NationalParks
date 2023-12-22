package com.example.nationalparks.presentation.main

interface MainListener: TopBarListener, BottomBarListener {
    fun openCamera(isOpen: Boolean)
}