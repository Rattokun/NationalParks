package com.example.nationalparks.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nationalparks.R
import com.example.nationalparks.presentation.main.TopBarListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(topBarListener: TopBarListener){
    TopAppBar(title = { Text(
        text = "My Plants",
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF333333),
        )
    ) }, actions = {
        Row {
            Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = null, modifier = Modifier
                .padding(end = 26.dp)
                .width(24.dp)
                .height(24.dp)
                .clickable {
                    topBarListener.onSearchClick()
                })
            Icon(painter = painterResource(id = R.drawable.ic_add_square), contentDescription = null,
                modifier = Modifier
                    .padding(end = 14.dp)
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        topBarListener.onAddClick()
                    })
        }
    })
}

@Preview
@Composable
fun PreviewToolBar(){
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
}