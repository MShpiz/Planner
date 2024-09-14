package com.layka.planner.ComposableFuncs.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.layka.planner.R
import com.layka.planner.data.TaskItem

@Composable
fun WeeklyCard(task: TaskItem, updateChecked: ()->Unit) {
    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = colorResource(R.color.light_mint),
        tagList = mutableListOf(Pair(stringResource(id = R.string.weekly_tag_text), colorResource(R.color.mint))),
        updateChecked = updateChecked
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