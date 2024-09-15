package com.layka.planner.composableFuncs.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.layka.planner.R
import com.layka.planner.data.TaskItem

@Composable
fun WeeklyCard(task: TaskItem, updateChecked: ()->Unit) {
    val tags = if (task.category != null){
        mutableListOf(Pair(task.category!!.categoryName, task.category!!.tagColor))
    } else {
        mutableListOf()
    }
    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = colorResource(R.color.light_mint),
        tagList = tags,
        updateChecked = updateChecked,
        typeText = stringResource(id = R.string.weekly_tag_text),
        doneDate = task.doneDate
    )
}

//@Composable
//@Preview
//fun WeeklyCardPreview() {
//    Column {
//        WeeklyCard(
//            TaskItem(null, "AAAAAAAAAA"),
//            taskViewModel
//        )
//        WeeklyCard(TaskItem(null, "Text", isDone = true), taskViewModel)
//    }
//}