package com.example.nationalparks.data.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.nationalparks.R

enum class BottomNav(
    @StringRes val title: Int,
    @StringRes val icon: Int,
    val route: String
) {
    HOME(title = R.string.home,
        icon = R.drawable.ic_home,
        route = MainDestinations.HOME),
    EXPLORE(title = R.string.explore,
        icon = R.drawable.ic_explore,
        route = MainDestinations.EXPLORE),
    ARTICLES(title = R.string.articles,
        icon = R.drawable.ic_book,
        route = MainDestinations.ARTICLES),
    SAVED(title = R.string.saved,
        icon = R.drawable.ic_saved,
        route = MainDestinations.SAVED),
    PROFILE(title = R.string.profile,
        icon = R.drawable.ic_profile,
        route = MainDestinations.PROFILE),
}