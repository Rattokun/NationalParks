package com.example.nationalparks.presentation

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nationalparks.R
import com.example.nationalparks.data.navigation.MainDestinations
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, isSplashScreen: MutableState<Boolean>) {
    val gradient =
        Brush.verticalGradient(
            listOf(
                Color(0xFF669353),
                Color(0xFF000000),
                Color(0xFF669353),
                Color(0xFFFFFFFF)
            )
        )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(273.dp)
        )
    }

    LaunchedEffect(Unit) {
        delay(3000)
        isSplashScreen.value = false
        navController.navigate(MainDestinations.HOME){
            popUpTo(MainDestinations.SPLASH){
                inclusive = true
            }
        }
    }
}