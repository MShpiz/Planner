package com.layka.planner.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.layka.planner.R
import com.layka.planner.ViewModels.FullListViewModel
import com.layka.planner.data.TaskItem

@Composable
fun PlainCard(task: TaskItem, updateChecked: ()->Unit) {
    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = colorResource(R.color.white),
        updateChecked = updateChecked
    )
}