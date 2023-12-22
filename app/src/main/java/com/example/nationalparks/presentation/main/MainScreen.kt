package com.example.nationalparks.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nationalparks.R
import com.example.nationalparks.domain.catalog.vo.AlertVO
import com.example.nationalparks.domain.catalog.vo.CategoryVO
import com.example.nationalparks.domain.catalog.vo.PlantsVO
import com.example.nationalparks.presentation.ui.AlertCard
import com.example.nationalparks.presentation.ui.BottomNavBar
import com.example.nationalparks.presentation.ui.CategoryCard
import com.example.nationalparks.presentation.ui.PlantCard
import com.example.nationalparks.presentation.ui.TopNavBar
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import java.util.jar.Manifest

@Composable
fun MainScreen(mViewModel: MainViewModel){
    val lazyListState = rememberLazyListState()
    LazyColumn(modifier = Modifier.padding(top = 50.dp), state = lazyListState) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(48.dp)
                .background(color = Color(0xFFEEF7E8), shape = RoundedCornerShape(size = 8.dp))
                .clickable { mViewModel.listener.openCamera(true) }) {
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

        //Popular plants
        item {
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .padding(top = 28.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()) {
                Text(
                    text = "Popular plants",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF333333),
                    )
                )
                Text(
                    text = "View all",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF61AF2B),
                        textDecoration = TextDecoration.Underline,
                    )
                )
            }
            val listPlants = listOf(
        PlantsVO(0, "Peperomia " +
                "Houseplant", R.drawable.first_plant),
        PlantsVO(0, "Asplenium" +
                "Houseplant", R.drawable.second_plant),
        PlantsVO(0, "Peperomia Houseplant", R.drawable.first_plant),
        PlantsVO(0, "Asplenium Houseplant", R.drawable.second_plant)
    )
            LazyRow(state = LazyListState(), modifier = Modifier.padding(top = 15.dp)){
                itemsIndexed(listPlants){ index, items ->
                    PlantCard(plantsVO = items, modifier = Modifier.padding(start = 8.dp))
                }
            }

            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 20.dp)
                .border(width = 0.5.dp, color = Color(0xFFD9D9D9)))
        }


        item {
            //Categories
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .padding(top = 4.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()) {
                Text(
                    text = "Categories",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF333333),
                    )
                )
                Text(
                    text = "View all",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF61AF2B),
                        textDecoration = TextDecoration.Underline,
                    )
                )
            }

            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 170.dp), horizontalArrangement = Arrangement.SpaceBetween, content = {
                itemsIndexed(CategoryVO.default){ index: Int, item: CategoryVO ->
                    CategoryCard(categoryVO = item, modifier = Modifier.padding(start = if(index % 2 == 0) 15.dp else 0.dp, top = 22.dp))
                }
            }, modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .height(152.dp))

            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 20.dp)
                .border(width = 0.5.dp, color = Color(0xFFD9D9D9)))
        }

        item {
            //Alerts
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .padding(top = 4.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()) {
                Text(
                    text = "Alerts for today",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF333333),
                    )
                )
                Text(
                    text = "View all",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF61AF2B),
                        textDecoration = TextDecoration.Underline,
                    )
                )
            }
        }
        item{
            AlertVO.default.forEachIndexed { index, alertVO ->  
                if(index == 0){
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                }
                
                AlertCard(alertVO = alertVO, modifier = Modifier.padding(top = 16.dp, start = 20.dp, end = 20.dp))
            }
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