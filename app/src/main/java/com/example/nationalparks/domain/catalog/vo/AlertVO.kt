package com.example.nationalparks.domain.catalog.vo

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import com.example.nationalparks.R

data class AlertVO(
    val id: Int,
    @StringRes val image: Int,
    val title: String,
    val preDescription: String
) {

    companion object {
        @SuppressLint("ResourceType")
        val default: List<AlertVO> = listOf(
            AlertVO(1, R.drawable.cactus, "Water your Cactus today (living room)","It’s 2 weeks old, you have to water it twice a w..."),
            AlertVO(2, R.drawable.bambuk, "Prune the dead branches of Bamboo t... ","It’s been 2-3 weeks since you have prune the d... "),
            AlertVO(3, R.drawable.cactus, "Water your Cactus today (living room)","It’s 2 weeks old, you have to water it twice a w..."),
            AlertVO(4, R.drawable.bambuk, "Prune the dead branches of Bamboo t... ","It’s been 2-3 weeks since you have prune the d... ")
        )
    }
}