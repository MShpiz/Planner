package com.layka.planner.data

import androidx.compose.ui.graphics.Color

data class TaskCategory(
    val categoryId: Int,
    val categoryName: String,
    val backgroundColor: Color = Color.White,
    val badgeColor: Color = Color.Gray
){

}
