package com.layka.planner.cards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.layka.planner.ViewModels.FullListViewModel
import com.layka.planner.data.TaskType

@Composable
fun  TaskList(taskViewModel: FullListViewModel = hiltViewModel()) {
    taskViewModel.getTasks()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            taskViewModel.tasks.value.forEach{
                when (it.taskType){
                    TaskType.DAILY ->
                        DailyCard(task = it.taskText, done = it.isDone)
                    TaskType.WEEKLY ->
                        WeeklyCard(task = it.taskText, done = it.isDone)
                    TaskType.DEFAULT -> {
                        if (it.category == null) {
                            PlainCard(task = it.taskText, done = it.isDone)
                        } else {
                            BaseCard(
                                task = it.taskText,
                                done = it.isDone,
                                backgroundColor = it.category.backgroundColor,
                                tagColor = it.category.badgeColor,
                                tagText = it.category.categoryName
                            )
                        }
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