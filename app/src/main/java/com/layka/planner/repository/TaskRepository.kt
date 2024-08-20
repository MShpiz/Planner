package com.layka.planner.repository

import androidx.compose.ui.graphics.Color
import com.layka.planner.data.TaskCategory
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType
import com.layka.planner.entities.TaskDatabase
import javax.inject.Inject

class TaskRepository @Inject constructor(private val database: TaskDatabase) {

    suspend fun getAllTasks(): MutableList<TaskItem> {
        val tasks = database.taskDao().getAll()
        val categories = database.taskCategoryDao().getAll()
        val result = mutableListOf<TaskItem>()
        for (t in tasks) {
            var cat: TaskCategory? = null
            val foundCategory = categories.find { it.id == t.categoryId }
            if (t.categoryId != null && foundCategory != null) {
                cat = TaskCategory(
                    foundCategory.id,
                    foundCategory.name,
                    Color( foundCategory.backgroundColor),
                    Color(foundCategory.tagColor)
                )
            }
            result.add(TaskItem(t.id, t.text, t.isDone, TaskType.DEFAULT, cat))
        }
        return result
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