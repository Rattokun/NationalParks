package com.example.nationalparks.presentation.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nationalparks.domain.catalog.vo.AlertVO

@Composable
fun SavedScreen() {
    val alertVOs = AlertVO.default

    LazyColumn {
        item {
            Text(
                text = "History",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF333333),
                    fontWeight = FontWeight(500)
                ),
                modifier = Modifier.padding(start = 20.dp, top = 60.dp)
            )
        }
        item { 
            Spacer(modifier = Modifier.padding(top = 30.dp))
        }
        gridItems(data = alertVOs.slice(setOf(1, 2)), nColumns = 2, horizontalArrangement = Arrangement.SpaceBetween,
            paddingValues = PaddingValues(5.dp)) {
            Box(
                modifier = Modifier
                    .width(162.dp)
                    .height(124.dp)
                    .padding()
                    .clip(RoundedCornerShape(18.dp))
            ){
                Image(painter = painterResource(id = it.fullImage), contentDescription = null, contentScale = ContentScale.FillBounds, modifier = Modifier
                    .fillMaxWidth())
                Text(text = it.animalName, style = TextStyle(fontSize = 16.sp, color = Color(0xFF333333), fontWeight = FontWeight(700)), modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(end = 5.dp))
            }
        }
    }
}

fun LazyListScope.gridItems(
    count: Int,
    nColumns: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(Int) -> Unit,
) {
    gridItems(
        data = List(count) { it },
        nColumns = nColumns,
        horizontalArrangement = horizontalArrangement,
        itemContent = itemContent,
    )
}

fun <T> LazyListScope.gridItems(
    data: List<T>,
    nColumns: Int,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    key: ((item: T) -> Any)? = null,
    paddingValues: PaddingValues = PaddingValues(),
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val rows = if (data.isEmpty()) 0 else 1 + (data.count() - 1) / nColumns
    items(rows) { rowIndex ->
        Row(horizontalArrangement = horizontalArrangement) {
            for (columnIndex in 0 until nColumns) {
                val itemIndex = rowIndex * nColumns + columnIndex
                if (itemIndex < data.count()) {
                    val item = data[itemIndex]
                    androidx.compose.runtime.key(key?.invoke(item)) {
                        Box(
                            modifier = Modifier
                                .weight(1f, fill = true)
                                .padding(paddingValues),
                            propagateMinConstraints = true
                        ) {
                            itemContent.invoke(this, item)
                        }
                    }
                } else {
                    Spacer(Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}