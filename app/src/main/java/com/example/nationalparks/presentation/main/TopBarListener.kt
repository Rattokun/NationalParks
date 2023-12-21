package com.example.nationalparks.presentation.main

interface TopBarListener {
    fun onSearchClick()

    fun onAddClick()

    fun getSearchText(text: String)
}