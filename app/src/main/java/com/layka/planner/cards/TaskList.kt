package com.layka.planner.cards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskList(tasks:List<TaskModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            tasks.forEach{
                if (it is DailyTaskModel) {
                    DailyCard(task = it.task, done = it.done)
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskListPreview() {
    TaskList()
}