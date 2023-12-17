package com.example.nationalparks

import android.content.res.Resources.Theme
import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nationalparks.ui.theme.NationalParksTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NationalParksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildAll()
                }
            }
        }
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
            Column (modifier = Modifier.fillMaxWidth(0.6F).align(Alignment.Center)){
                Image(painter = cam, contentDescription = null, contentScale = ContentScale.FillWidth)
                Text(text = "Take Photo")
            }
        }
        Box(modifier = Modifier
            .size(75.dp)
            .clip(RoundedCornerShape(1.dp))
            .border(BorderStroke(2.dp, Color.Black))){
            Column (modifier = Modifier.fillMaxWidth(0.6F).align(Alignment.Center)) {
                Image(painter = magick, contentDescription = null, contentScale = ContentScale.FillWidth)
                Text(text = "Identify")
            }
        }
        Box(modifier = Modifier
            .size(75.dp)
            .clip(RoundedCornerShape(1.dp))
            .border(BorderStroke(2.dp, Color.Black))){
            Column (modifier = Modifier.fillMaxWidth(0.6F).align(Alignment.Center)) {
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