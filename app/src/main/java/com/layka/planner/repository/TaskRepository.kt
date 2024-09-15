package com.layka.planner.repository

import android.util.Log
import com.layka.planner.data.TaskCategory
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType
import com.layka.planner.repository.entities.CategoryDb
import com.layka.planner.repository.entities.TaskDb
import com.layka.planner.network.BackupApi
import com.layka.planner.network.TaskRequest
import retrofit2.HttpException
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val database: DatabaseAPI,
    private val outerRepo: BackupApi
) {

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
                    foundCategory.tagColor
                )
            }
            Log.v("CAT_VAL", "db ${cat.toString()}")
            result.add(TaskItem(t.id, t.text, t.isDone, t.type, cat, t.dateDone))
        }
        return result
    }

    suspend fun getTaskDetails(id: Long): TaskItem? {
        val task: TaskDb
        try {
            task = database.taskDao().getTaskById(id)
        } catch (e: Exception) {
            return null
        }
        var category: TaskCategory? = null
        if (task.categoryId != null) {
            val tmp = database.taskCategoryDao().getCategoryById(task.categoryId)
            category = TaskCategory(tmp.id, tmp.name, tmp.tagColor)
        }

        return TaskItem(id = task.id,taskText = task.text, isDone = task.isDone, taskType = task.type, category = category)
    }

    suspend fun saveTask(value: TaskItem) {
        if (value.id == null || (getTaskDetails(value.id) == null)) {
            database.taskDao().insertTask(TaskDb(value.taskText, value.isDone, value.taskType, value.category?.categoryId, null))
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

    suspend fun getAllTasksByType(taskType: TaskType): List<TaskItem> {
        val tasks = database.taskDao().getTasksByType(taskType.ordinal)
            .map { TaskItem(it.id, it.text, it.isDone, it.type, null) }
        Log.v("GET_TASKS", "${taskType.name} ${tasks.size}")
        return tasks
    }

    suspend fun getAllTasksByCategory(taskCategory: TaskCategory): List<TaskItem> {
        return database.taskDao()
            .getTasksOfCategory(taskCategory.categoryId)
            .tasks
            .map{
                TaskItem(it.id, it.text, it.isDone, it.type, taskCategory, it.dateDone)
            }
    }

    private suspend fun updateTask(taskItem: TaskItem) {
        database.taskDao().updateTask(TaskDb(taskItem.taskText, taskItem.isDone, taskItem.taskType, taskItem.category?.categoryId, taskItem.doneDate, taskItem.id!!))
    }


    suspend fun syncDatabase(): Boolean  {
        val result: TaskRequest =
        try{
            outerRepo.getTasks()
        } catch (e: HttpException) {
            Log.v("SYNC_ERROR", e.message())
            return false
        } catch (e: Exception) {
            Log.v("SYNC_ERROR", e.message.toString())
            return false
        }

        Log.v("SYNC_ERROR", result.tasks.isEmpty().toString())

        val currentState = database.taskDao().getAll()

        if (currentState.isEmpty()) {
            insertTasks(result, currentState)
            return true
        } else {
            try {
                outerRepo.postTasks(TaskRequest(currentState))
            } catch (e: HttpException) {
                Log.v("SYNC_ERROR", e.message())
                return false
            } catch (e: Exception) {
                Log.v("SYNC_ERROR", e.message.toString())
                return false
            }
        }
        return true

    }

    private fun insertTasks(result: TaskRequest, currentState: List<TaskDb>) {
        val difference = result.tasks.toSet().minus(currentState.toSet()).toList()

        for (task in difference) {
            // Log.v("SYNC_ERROR", task.text)
            database.taskDao().insertTask(task)
        }

    }

    suspend fun getAllCategories(): List<TaskCategory> {
        return database.taskCategoryDao().getAll().map { TaskCategory(it.id, it.name, it.tagColor) }
    }

    suspend fun getCategoryDetails(id: Long): TaskCategory? {
        val result =
            try {
                database.taskCategoryDao().getCategoryById(id)
            } catch (e: Exception) {
                return null
            }
        return TaskCategory(result.id, result.name, result.tagColor)
    }

    suspend fun saveCategory(taskCategory: TaskCategory) {
        if (taskCategory.categoryId == null || database.taskCategoryDao().getCategoryById(taskCategory.categoryId) == null) {
            database.taskCategoryDao().insertCategory(
                CategoryDb(
                    taskCategory.categoryName,
                    taskCategory.tagColor
                )
            )
        } else{
            database.taskCategoryDao().updateCategory(
                CategoryDb(
                    taskCategory.categoryName,
                    taskCategory.tagColor,
                    taskCategory.categoryId
                )
            )
        }
    }

    suspend fun deleteCategory(id: Long) {
        val cat = database.taskCategoryDao().getCategoryById(id)
        if (cat == null) {
            return
        }
        database.taskDao().getTasksOfCategory(id).tasks.map{
            database.taskDao().deleteTask(it)
        }
        database.taskCategoryDao().deleteCategoryById(cat)
    }
}