package com.example.nationalparks.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nationalparks.R
import com.example.nationalparks.presentation.ui.BottomNavBar
import com.example.nationalparks.presentation.ui.TopNavBar

@Composable
fun MainScreen(mViewModel: MainViewModel){
    Column(modifier = Modifier.padding(top = 50.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(48.dp)
            .background(color = Color(0xFFEEF7E8), shape = RoundedCornerShape(size = 8.dp))) {
            Icon(painter = painterResource(id = R.drawable.ic_scan), contentDescription = "", modifier = Modifier.size(24.dp))
            Text(
                text = "Scan and identify the plant",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF61AF2B),
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}



@Composable
fun ProfileScreen() {
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile",
            tint = Color(0xFF0F9D58)
        )
        // Text to Display the current Screen
        Text(text = "Profile", color = Color.Black)
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewMainScreen(){
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            TopNavBar(topBarListener = object : TopBarListener{
                override fun onSearchClick() {
                    TODO("Not yet implemented")
                }

                override fun onAddClick() {
                    TODO("Not yet implemented")
                }

                override fun getSearchText(text: String) {
                    TODO("Not yet implemented")
                }

            })
        },
        bottomBar = {
//            BottomNavBar()
        }
    ) {
        MainScreen(mViewModel = hiltViewModel())
    }
}