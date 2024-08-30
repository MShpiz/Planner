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
fun WeeklyCard(task: TaskItem, updateChecked: ()->Unit) {
    BaseCard(
        task.taskText,
        task.isDone,
        backgroundColor = colorResource(R.color.light_mint),
        tagColor = colorResource(R.color.mint),
        tagText = stringResource(id = R.string.weekly_tag_text),
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