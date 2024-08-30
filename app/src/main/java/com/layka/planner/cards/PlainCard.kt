package com.layka.planner.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.layka.planner.R
import com.layka.planner.data.TaskItem

@Composable
fun PlainCard(task: TaskItem) {
    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = colorResource(R.color.white)
    )
}