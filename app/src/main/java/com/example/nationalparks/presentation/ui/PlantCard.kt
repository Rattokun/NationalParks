package com.example.nationalparks.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nationalparks.domain.catalog.vo.PlantsVO

@SuppressLint("ResourceType")
@Composable
fun PlantCard(plantsVO: PlantsVO, modifier: Modifier = Modifier){
    val configuration = LocalConfiguration.current
    val widthCard = configuration.screenWidthDp.dp/2

    Box(
        modifier = modifier
            .width(widthCard)
            .height(130.dp)
            .background(color = Color(0xFFF8F8F8), shape = RoundedCornerShape(size = 12.dp))
    ){
        Image(painter = painterResource(id = plantsVO.image), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(144.dp)
                .align(Alignment.BottomEnd).padding(bottom = 10.dp, start = 20.dp))
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 12.dp, start = 12.dp).width(89.dp)) {
            Text(
                text = "Fits well",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF61AF2B),
                )
            )

            Text(
                text = plantsVO.title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF333333),
                )
            )
        }

    }

}

@Preview
@Composable
fun PreviewPlantCard(){
//    PlantCard(plantsVO = PlantsVO().default[1])
}