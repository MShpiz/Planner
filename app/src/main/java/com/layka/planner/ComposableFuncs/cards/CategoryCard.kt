package com.layka.planner.ComposableFuncs.cards

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.layka.planner.R
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType

@Composable
fun CategoryCard(task: TaskItem, updateChecked: () -> Unit) {

    val tags = mutableListOf(Pair(task.category!!.categoryName, task.category!!.tagColor))

    when (task.taskType) {
        TaskType.DEFAULT -> {}
        TaskType.DAILY -> {tags.add(Pair(
            stringResource(id = R.string.daily_tag_text), colorResource(
                R.color.light_blue)
        ))}
        TaskType.WEEKLY -> {
            tags.add(Pair(stringResource(id = R.string.weekly_tag_text), colorResource(R.color.mint)))
        }
    }


    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = (task.category!!.backgroundColor) ?: Color.White,
        tagList = tags,
        updateChecked = updateChecked
    )
}