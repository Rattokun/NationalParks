package com.example.nationalparks.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nationalparks.R

@Composable
fun DetailScreen(isSlashScreen: MutableState<Boolean>) {
    isSlashScreen.value = true
    Box {
        Image(
            painter = painterResource(id = R.drawable.full_mooses),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxHeight()
                .padding(top = 220.dp)
                .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                .background(color = Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .padding(start = 20.dp, top = 28.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .background(color = Color(0xFFEEF9E6), shape = RoundedCornerShape(20.dp))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_tick),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(10.dp)
                            .height(7.dp)
                    )
                }
                Text(
                    text = "Hurray, we identified animal!",
                    style = TextStyle(fontSize = 16.sp, color = Color(0xFF61AF2B)),
                    modifier = Modifier
                        .padding(start = 6.dp)
                )
            }

            Text(
                text = "Moose, the boose",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(700)
                ),
                modifier = Modifier
                    .padding(start = 20.dp, top = 17.dp)
            )

            Row(
                modifier = Modifier
                    .padding(start = 20.dp, top = 19.dp)
            ) {
                val tags = listOf("#Moose", "#WildAnimals", "#MooseHabitat")
                tags.forEachIndexed { index, s ->
                    Box(
                        modifier = Modifier
                            .padding(start = if (index != 0) 16.dp else 0.dp)
                            .height(24.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(color = Color(0xFFF0F3F6))
                    ) {
                        Text(
                            text = s,
                            style = TextStyle(fontSize = 12.sp, color = Color(0xFF696969)),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 10.dp)
                        )
                    }
                }
            }

            Text(
                text = "Description",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(700)
                ),
                modifier = Modifier
                    .padding(start = 20.dp, top = 17.dp)
            )
            Text(
                text = "From Wikipedia, the free encyclopedia",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF8C8C8C),
                    fontWeight = FontWeight(400)
                ),
                modifier = Modifier
                    .padding(start = 20.dp, top = 4.dp)
            )
            Text(
                text = "The largest member of the deer family, moose stand up to seven feet tall, sporting dark brown fur and a shoulder hump." +
                        " Female moose (3.5+ years old) average 836 pounds and the average prime age (5.5+) adult bull weighs 1,106 pounds.",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF515151),
                    fontWeight = FontWeight(400)
                ), modifier = Modifier
                    .padding(start = 20.dp, top = 4.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 200.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF61AF2B))) {
                Icon(painter = painterResource(id = R.drawable.ic_saved), contentDescription = null, tint = Color.White, modifier = Modifier
                    .height(22.dp)
                    .width(18.dp))
                Text(text = "Go to map", style = TextStyle(fontSize = 18.sp, color = Color.White), modifier = Modifier
                    .padding(start = 30.dp, top = 17.dp, bottom = 17.dp))
            }

        }
    }
}

@Preview
@Composable
fun Preview() {
    DetailScreen(mutableStateOf(false))
}