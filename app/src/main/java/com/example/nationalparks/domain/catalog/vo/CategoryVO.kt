package com.example.nationalparks.domain.catalog.vo

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.nationalparks.R

data class CategoryVO(
    val id: Int = 0,
    val title: String = "",
    @StringRes val icon: Int = 0,
    val total: Int = 2,
    val color: Long = 0x00000000,
    val iconColor: Color = Color.Black
) {

    companion object {
        @SuppressLint("ResourceType")
        val default: List<CategoryVO> = listOf(
            CategoryVO(0, "Living Room", R.drawable.ic_living_room, 2, 0xFFEEF7E8, Color(0xFF4B8364)),
            CategoryVO(1, "Kitchen", R.drawable.ic_coffee, 3, 0xFFE6EAFA, Color(0xFF5676DC)),
            CategoryVO(2, "Drawing Room", R.drawable.ic_room, 6, 0xFFFCF1E3, Color(0xFFE6B44C)),
            CategoryVO(3, "Backyard ", R.drawable.ic_backyard, 8, 0xFFF8E8F8, Color(0xFFA559D9))
        )
    }
}