package com.example.nationalparks.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nationalparks.data.navigation.BottomNav

@SuppressLint("ResourceType")
@Composable
fun BottomNavBar(changeScreen: (BottomNav) -> Unit, navController: NavController, modifier: Modifier = Modifier){
    val listScreens = BottomNav.values()
    val listRoutes = listScreens.map { it.route }

    NavigationBar(modifier = modifier, containerColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        listScreens.forEachIndexed { index, bottomNav ->
            NavigationBarItem(
                selected = currentRoute == bottomNav.route,
                onClick = {
                    changeScreen(bottomNav)
                },
                icon = { Icon(painter = painterResource(id = bottomNav.icon), contentDescription = null) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Unspecified, selectedTextColor = Color(0xFF61AF2B)
                ),
                label = {
                    Text(
                        text = "Explore",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 21.sp,
                            fontWeight = FontWeight(500),
                            color = if(currentRoute == bottomNav.route) Color(0xFF61AF2B) else Color(0xFF8C8C8C),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            )
        }
    }

}