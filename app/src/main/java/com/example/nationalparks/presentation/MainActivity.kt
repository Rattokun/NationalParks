package com.example.nationalparks.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nationalparks.presentation.ui.theme.NationalParksTheme
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.nationalparks.R
import com.example.nationalparks.data.navigation.BottomNav
import com.example.nationalparks.data.navigation.MainDestinations
import com.example.nationalparks.presentation.main.MainScreen
import com.example.nationalparks.presentation.main.MainViewModel
import com.example.nationalparks.presentation.main.ProfileScreen
import com.example.nationalparks.presentation.ui.BottomNavBar
import com.example.nationalparks.presentation.ui.TopNavBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//
//    @Inject
//    lateinit var mViewModel: MainViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mViewModel: MainViewModel = hiltViewModel()
            val navController = rememberNavController()
            val currentScreen = mViewModel.currentScreen.collectAsState()
            NationalParksTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    Scaffold(
                        topBar = {
                            TopNavBar(mViewModel.listener)
                        },
                        bottomBar = {
                            BottomNavBar(navController = navController,
                                changeScreen = mViewModel.listener::onChangeScreen)
                        }
                    ) {
                        NavHost(navController = navController, startDestination = currentScreen.value) {
                            addHomeGraph(mainViewModel = mViewModel)
                        }

//                        NavHost(navController = navController, startDestination = currentScreen.value){
//                            navGraph(mViewModel)
//                        }
                    }
                }
            }
        }
    }
}

fun NavGraphBuilder.navGraph(mainViewModel: MainViewModel) {
    navigation(
        route = MainDestinations.HOME,
        startDestination = BottomNav.HOME.route
    ) {
        addHomeGraph(mainViewModel = mainViewModel)
    }
}

fun NavGraphBuilder.addHomeGraph(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel
) {
    composable(BottomNav.HOME.route) {
        MainScreen(mainViewModel)
    }
    composable(BottomNav.PROFILE.route) {
        ProfileScreen()
    }
    composable(BottomNav.EXPLORE.route) {
        MainScreen(mainViewModel)
    }
    composable(BottomNav.ARTICLES.route) {
        MainScreen(mainViewModel)
    }
    composable(BottomNav.SAVED.route) {
        MainScreen(mainViewModel)
    }
}


@Composable
fun BuildAll(){
    Column(modifier = Modifier.padding(16.dp)) {
        StartOfWork()
        TakePhoto()
    }
}

@Composable
fun TakePhoto(){
    val cam = painterResource(id = R.drawable.base_camera)
    val magick = painterResource(id = R.drawable.base_magick)
    val add = painterResource(id = R.drawable.baseline_add_24)
    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween)
    {
        Box(modifier = Modifier
            .size(75.dp)
            .clip(RoundedCornerShape(1.dp))
            .border(BorderStroke(2.dp, Color.Black))){
            Column (modifier = Modifier
                .fillMaxWidth(0.6F)
                .align(Alignment.Center)){
                Image(painter = cam, contentDescription = null, contentScale = ContentScale.FillWidth)
                Text(text = "Take Photo")
            }
        }
        Box(modifier = Modifier
            .size(75.dp)
            .clip(RoundedCornerShape(1.dp))
            .border(BorderStroke(2.dp, Color.Black))){
            Column (modifier = Modifier
                .fillMaxWidth(0.6F)
                .align(Alignment.Center)) {
                Image(painter = magick, contentDescription = null, contentScale = ContentScale.FillWidth)
                Text(text = "Identify")
            }
        }
        Box(modifier = Modifier
            .size(75.dp)
            .clip(RoundedCornerShape(1.dp))
            .border(BorderStroke(2.dp, Color.Black))){
            Column (modifier = Modifier
                .fillMaxWidth(0.6F)
                .align(Alignment.Center)) {
                Image(painter = add, contentDescription = null, contentScale = ContentScale.FillWidth)
                Text(text = "Add Photo")
            }
        }
    }
}

@Composable
fun StartOfWork() {
    val image = painterResource(R.drawable.header)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        Text("Welcome to National")
        Text("Start by taking a picture of animal")
        }
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NationalParksTheme {
        BuildAll()
    }
}