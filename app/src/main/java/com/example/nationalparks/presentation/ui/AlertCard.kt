package com.example.nationalparks.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nationalparks.R
import com.example.nationalparks.domain.catalog.vo.AlertVO

@SuppressLint("ResourceType")
@Composable
fun AlertCard(alertVO: AlertVO, modifier: Modifier = Modifier){
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = alertVO.image),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(50.dp).background(color = Color(0x08000000), shape = RoundedCornerShape(size = 6.dp))
        )

        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = alertVO.title,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF333333),
                )
            )

            Text(
                text = alertVO.preDescription,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF628093),
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "image description",
            tint = Color(0xFF628093),
            modifier = Modifier.size(24.dp)
        )
    }
}