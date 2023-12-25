package com.example.nationalparks.presentation

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.PreviewView
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import com.example.nationalparks.presentation.ui.theme.NationalParksTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//
//    @Inject
//    lateinit var mViewModel: MainViewModel

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var mViewModel: MainViewModel
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestCameraPermission()

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
        setContent {
            mViewModel = hiltViewModel()
            val navController = rememberNavController()
            val currentScreen = mViewModel.currentScreen.collectAsState()
            val shouldShowCamera = mViewModel.shouldShowCamera.collectAsState()
            val fPlants = mViewModel.fPlants.collectAsState()
            val gson = Gson()
            println(gson.toJson(fPlants.value))
            if (shouldShowCamera.value) {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = { Log.e("kilo", "View error:", it) }
                )
            }
            else {
                NationalParksTheme {
                    // A surface container using the 'background' color from the theme
                    Surface {
                        Scaffold(
                            topBar = {
                                TopNavBar(mViewModel.listener)
                            },
                            bottomBar = {
                                BottomNavBar(
                                    navController = navController,
                                    changeScreen = mViewModel.listener::onChangeScreen
                                )
                            }
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = currentScreen.value
                            ) {
                                addHomeGraph(mainViewModel = mViewModel)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
        mViewModel.isOpenCamera(false)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Permission granted")
        } else {
            Log.i("kilo", "Permission denied")
        }
    }
    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("kilo", "Permission previously granted")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
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
fun CameraView(
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    // 1
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current


    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    // 2
    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            imageCapture
        )


    }

    // 3
    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

        IconButton(
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = {
                Log.i("kilo", "ON CLICK")
                takePhoto(
                    filenameFormat = "yyyy-MM-dd-HH-mm-ss-SSS",
                    imageCapture = imageCapture,
                    outputDirectory = outputDirectory,
                    executor = executor,
                    onImageCaptured = onImageCaptured,
                    onError = onError
                )
            },
            content = {
                Icon(
                    imageVector = Icons.Sharp.AddCircle,
                    contentDescription = "Take picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        )
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