package com.layka.planner.data

data class TaskItem(
    val id: Long?,
    var taskText: String,
    var isDone: Boolean = false,
    var taskType: TaskType = TaskType.DEFAULT,
    val category: TaskCategory? = null,
)