package com.layka.planner.data

import java.time.LocalDate
import java.util.Date

data class TaskItem(
    val id: Long?,
    var taskText: String,
    var isDone: Boolean = false,
    var taskType: TaskType = TaskType.DEFAULT,
    var category: TaskCategory? = null,
    var doneDate: LocalDate? = null
) {
    override fun toString(): String {
        return "$id $taskText $taskType $isDone $doneDate"
    }
}