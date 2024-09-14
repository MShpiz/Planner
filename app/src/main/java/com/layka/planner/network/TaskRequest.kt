package com.layka.planner.network

import com.layka.planner.repository.entities.TaskDb

data class TaskRequest (
    val tasks: List<TaskDb>
)