package com.layka.planner.data

import java.time.LocalDate
import java.util.Date

data class TaskItem(
    val id: Long?,
    var taskText: String,
    var isDone: Boolean = false,
    var taskType: TaskType = TaskType.DEFAULT,
    val category: TaskCategory? = null,
    var doneDate: Date? = null
)