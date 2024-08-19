package com.layka.planner.repository

import com.layka.planner.data.TaskCategory
import com.layka.planner.data.TaskType

class TaskRepository {
    suspend fun getAllTasks() {
        TODO("getAllTasks")
    }

    suspend fun getTaskDetails(id: Long) {
        TODO("getTaskDetails")
    }

    suspend fun saveNewTask() {
        TODO("saveNewTask")
    }

    suspend fun deleteTask() {
        TODO("deleteTask")
    }

    suspend fun getAllTasksByType(taskType: TaskType) {
        TODO("getAllTasksByType")
    }

    suspend fun getAllTasksByCategory(taskCategory: TaskCategory) {
        TODO("getAllTasksByCategory")
    }

    suspend fun updateTask() {
        TODO("updateTask")
    }


    // пока работа с бекапами будет тут
//    suspend fun updateOuterRepository() {
//
//    }
//
//    suspend fun getDataFromOuterRepository() {
//
//    }
}