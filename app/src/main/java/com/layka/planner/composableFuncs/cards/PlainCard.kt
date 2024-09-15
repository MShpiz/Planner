package com.layka.planner.composableFuncs.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.layka.planner.R
import com.layka.planner.data.TaskItem

@Composable
fun PlainCard(task: TaskItem, updateChecked: ()->Unit) {
    val tags = if (task.category != null){
         mutableListOf(Pair(task.category!!.categoryName, task.category!!.tagColor))
    } else {
        mutableListOf()
    }

    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = colorResource(R.color.white),
        tagList = tags,
        updateChecked = updateChecked,
        doneDate = task.doneDate
    )
}