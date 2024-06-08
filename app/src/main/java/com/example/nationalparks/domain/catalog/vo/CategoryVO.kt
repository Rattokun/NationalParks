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
                CategoryVO(0, "Bears", R.drawable.ic_bear, 2, 0xFFEEF7E8, Color(0xFF4B8364)),
            CategoryVO(1, "Big Cats", R.drawable.ic_cat, 3, 0xFFE6EAFA, Color(0xFF5676DC)),
            CategoryVO(2, "Dogs", R.drawable.ic_dog, 6, 0xFFFCF1E3, Color(0xFFE6B44C)),
            CategoryVO(3, "Mooses ", R.drawable.ic_moose, 8, 0xFFF8E8F8, Color(0xFFA559D9))
        )
    }
}