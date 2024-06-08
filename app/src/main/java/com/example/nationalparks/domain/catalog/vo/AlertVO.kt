package com.example.nationalparks.domain.catalog.vo

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import com.example.nationalparks.R

data class AlertVO(
    val id: Int,
    @StringRes val image: Int,
    val title: String,
    val preDescription: String,
    val animalName: String = "",
    @StringRes val fullImage: Int
) {

    companion object {
        @SuppressLint("ResourceType")
        val default: List<AlertVO> = listOf(
            AlertVO(
                1,
                R.drawable.bg_saltwater_crocodile,
                "Increased Crocodile Activity in Local Waters",
                "It’s 2 weeks old, you have to water it twice a w...",
                "Owl",
                R.drawable.full_owl
            ),
            AlertVO(
                2,
                R.drawable.bg_siberischer_tiger,
                "Residents and visitors are advised to exercise... ",
                "It’s been 2-3 weeks since you have prune the d... ",
                "Tiger",
                R.drawable.full_tiger
            ),
            AlertVO(
                3,
                R.drawable.bg_saltwater_crocodile,
                "Increased Crocodile Activity in Local Waters",
                "It’s 2 weeks old, you have to water it twice a w...",
                "Owl",
                R.drawable.full_owl
            ),
            AlertVO(
                4,
                R.drawable.bg_siberischer_tiger,
                "Residents and visitors are advised to exercise... ",
                "It’s been 2-3 weeks since you have prune the d... ",
                "Tiger",
                R.drawable.full_tiger
            )
        )
    }
}