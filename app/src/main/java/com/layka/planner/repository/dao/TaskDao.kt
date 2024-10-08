package com.layka.planner.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.layka.planner.repository.entities.CategoriesWithTasks
import com.layka.planner.repository.entities.TaskDb

@Dao
interface TaskDao {
    @Insert
    fun insertTask(taskDb: TaskDb)
    @Update
    fun updateTask(taskDb: TaskDb)
    @Upsert
    fun upsertTask(taskDb: TaskDb)

    @Delete
    fun deleteTask(taskDb: TaskDb)

    @Transaction
    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<TaskDb>

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId ")
    suspend fun getTaskById(taskId: Long): TaskDb

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskType = :type")
    suspend fun getTasksByType(type: Int): List<TaskDb>

    @Transaction
    @Query("SELECT * FROM taskcategories WHERE id = :categoryId")
    suspend fun getTasksOfCategory(categoryId: Long?): CategoriesWithTasks
}