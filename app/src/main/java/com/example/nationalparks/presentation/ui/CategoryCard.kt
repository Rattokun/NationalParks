package com.example.nationalparks.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nationalparks.R
import com.example.nationalparks.domain.catalog.vo.CategoryVO

@SuppressLint("ResourceType")
@Composable
fun CategoryCard(categoryVO: CategoryVO, modifier: Modifier = Modifier){
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(
                    color = Color(categoryVO.color),
                    shape = RoundedCornerShape(size = 12.dp)
                ),
        ){
            Icon(painter = painterResource(id = categoryVO.icon), contentDescription = null, tint = categoryVO.iconColor, modifier = Modifier
                .size(25.dp)
                .align(Alignment.Center))
        }
        Column(modifier = Modifier.padding(start = 14.dp)) {
            Text(
                text = "${categoryVO.total} species",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF628093),
                )
            )

            Text(
                text = categoryVO.title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF333333),
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}