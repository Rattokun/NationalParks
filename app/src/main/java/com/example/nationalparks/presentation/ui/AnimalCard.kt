package com.example.nationalparks.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nationalparks.R
import com.example.nationalparks.domain.catalog.vo.PlantsVO

@Composable
fun AnimalCard(animal: PlantsVO, modifier: Modifier = Modifier){
    Box(modifier = modifier
        .height(124.dp)
        .width(164.dp)
        .clip(RoundedCornerShape(18))){
        Image(painter = painterResource(id = animal.image), contentDescription = null, contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(18)))
        Text(text = animal.title, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFFFFFFFF),
        ), modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 10.dp, bottom = 10.dp))
    }
}

@Preview
@Composable
fun Preview(){
    Row {
        AnimalCard(animal = PlantsVO(title = "Moose \n" +
                "the boose", image = R.drawable.bg_moose))
        AnimalCard(animal = PlantsVO(title = "Raccoon\n" +
                "the robber", image = R.drawable.bg_racoon))
    }
}


