package com.layka.planner.ComposableFuncs.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.layka.planner.R
import com.layka.planner.data.TaskItem

@Composable
fun DailyCard(task: TaskItem, updateChecked: ()->Unit) {
    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = colorResource(R.color.baby_blue),
        tagList = listOf(Pair(stringResource(id = R.string.daily_tag_text), colorResource(R.color.light_blue))),
        updateChecked = updateChecked
    )
}

//@Composable
//@Preview
//fun DalyCardPreview() {
//    Column {
//        DailyCard(
//            TaskItem(null, "AAAAAAAAAA"),
//            taskViewModel
//        )
//        DailyCard(TaskItem(null, "Text", isDone = true), taskViewModel)
//    }
//}