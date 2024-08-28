package com.layka.planner.repository

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
            result.add(TaskItem(t.text, t.isDone, t.type, cat))
        }
        return result
    }

    suspend fun getTaskDetails(id: Int): TaskItem? {
        val task:TaskDb
        try {
            task = database.taskDao().getTaskById(id)
        } catch (e: Exception) {
            return null
        }
        return TaskItem(taskText = task.text, isDone = task.isDone, taskType = task.type)
    }

    suspend fun saveNewTask(value: TaskItem) {
        database.taskDao().insertTask(TaskDb(value.taskText, value.isDone, value.taskType, value.category?.categoryId))
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

    suspend fun addTask(taskItem: TaskItem) {
        database.taskDao().insertTask(TaskDb(taskItem.taskText, taskItem.isDone, taskItem.taskType,taskItem.category?.categoryId ?: null))
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