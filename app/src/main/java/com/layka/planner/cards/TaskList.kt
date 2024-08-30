package com.layka.planner.cards

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.layka.planner.ViewModels.FullListViewModel
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType

@Composable
fun  TaskList(taskViewModel: FullListViewModel = hiltViewModel(), navController: NavController) {
    taskViewModel.getTasks()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(taskViewModel.tasks.value) { index: Int, it: TaskItem ->
                Box(
                    modifier = Modifier.clickable {
                        Log.v("ClickTrack", "clicked box of item ${it.id}")
                        navController.navigate("edit_task/${it.id}")
                    }
                ) {
                    when (it.taskType) {
                        TaskType.DAILY ->
                            DailyCard(it)

                        TaskType.WEEKLY ->
                            WeeklyCard(it)

                        TaskType.DEFAULT -> {
                            PlainCard(it)
                        }
                    }
                }
            }
        }
    }


@Preview
@Composable
fun TaskListPreview() {
//    val customCategory: TaskCategory = TaskCategory(1, "cat", Color.Gray, Color.Red)
//    val tasks = listOf<TaskItem>(
//        TaskItem(1, "Task1"),
//        TaskItem(2, taskText = "Task2", isDone = true),
//        TaskItem(3, taskText = "Daily task", taskType = TaskType.DAILY),
//        TaskItem(4, taskText = "Weekly task", taskType = TaskType.WEEKLY),
//        TaskItem(6, taskText = "Custom task", category = customCategory),
//    )
//    TaskList(tasks = tasks)
}