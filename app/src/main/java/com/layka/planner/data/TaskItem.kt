package com.layka.planner.data

data class TaskItem(
    var taskText: String,
    val isDone: Boolean = false,
    var taskType: TaskType = TaskType.DEFAULT,
    val category: TaskCategory? = null
)