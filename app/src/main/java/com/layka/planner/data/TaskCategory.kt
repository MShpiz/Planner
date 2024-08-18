package com.layka.planner.data

import androidx.compose.ui.graphics.Color

data class TaskCategory(
    val categoryId: Long,
    val categoryName: String,
    val backgroundColor: Color,
    val badgeColor: Color
)
