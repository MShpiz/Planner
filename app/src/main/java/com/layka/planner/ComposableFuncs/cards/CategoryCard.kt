package com.layka.planner.ComposableFuncs.cards

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.layka.planner.data.TaskItem

@Composable
fun CategoryCard(task: TaskItem, updateChecked: () -> Unit) {
    Log.v("CAT_VAL", task.category.toString())
    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = (task.category?.backgroundColor) ?: Color.White,
        tagColor = task.category?.tagColor ?: Color.Black,
        tagText = task.category?.categoryName,
        updateChecked = updateChecked
    )
}