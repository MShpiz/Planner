package com.layka.planner.data

import androidx.compose.ui.graphics.Color

data class TaskCategory(
    val categoryId: Long?,
    val categoryName: String,
    val tagColor: Color = Color.Gray
){
    override fun toString(): String {
        return "$categoryId $categoryName  $tagColor"
    }
}
