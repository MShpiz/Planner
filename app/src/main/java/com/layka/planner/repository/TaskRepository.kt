package com.layka.planner.repository

import android.util.Log
import com.layka.planner.data.TaskCategory
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType
import com.layka.planner.entities.TaskDb
import javax.inject.Inject

class TaskRepository @Inject constructor(private val database: DatabaseAPI) {

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
                    foundCategory.backgroundColor,
                    foundCategory.tagColor
                )
            }
            result.add(TaskItem(t.id, t.text, t.isDone, t.type, cat))
        }
        return result
    }

    suspend fun getTaskDetails(id: Long): TaskItem? {
        val task:TaskDb
        try {
            task = database.taskDao().getTaskById(id)
        } catch (e: Exception) {
            return null
        }
        return TaskItem(id = task.id,taskText = task.text, isDone = task.isDone, taskType = task.type)
    }

    suspend fun saveTask(value: TaskItem) {
        if (value.id == null || (getTaskDetails(value.id) == null)) {
            database.taskDao().insertTask(TaskDb(value.taskText, value.isDone, value.taskType, value.category?.categoryId))
        } else {
            updateTask(value)
        }

    }

    suspend fun deleteTask(id: Long) {
        val task = database.taskDao().getTaskById(id)

        if (task != null){
            database.taskDao().deleteTask(task)
        }

    }

    suspend fun getAllTasksByType(taskType: TaskType) {
        TODO("getAllTasksByType")
    }

    suspend fun getAllTasksByCategory(taskCategory: TaskCategory) {
        TODO("getAllTasksByCategory")
    }

    private suspend fun updateTask(taskItem: TaskItem) {
        database.taskDao().updateTask(TaskDb(taskItem.taskText, taskItem.isDone, taskItem.taskType, taskItem.category?.categoryId, taskItem.id!!))
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