package com.layka.planner.data

data class TaskItem(
    val taskText: String,
    val isDone: Boolean = false,
    val taskType: TaskType = TaskType.DEFAULT,
    val category: TaskCategory? = null
)