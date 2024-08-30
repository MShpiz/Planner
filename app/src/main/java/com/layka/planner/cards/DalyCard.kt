package com.layka.planner.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.layka.planner.R
import com.layka.planner.ViewModels.FullListViewModel
import com.layka.planner.data.TaskItem

@Composable
fun DailyCard(task: TaskItem, updateChecked: ()->Unit) {
    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = colorResource(R.color.baby_blue),
        tagText = stringResource(id = R.string.daily_tag_text),
        tagColor = colorResource(R.color.light_blue),
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