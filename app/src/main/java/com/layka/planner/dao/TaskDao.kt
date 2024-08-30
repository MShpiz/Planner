package com.layka.planner.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.layka.planner.entities.TaskDb

@Dao
interface TaskDao {
    @Insert
    fun insertTask(taskDb: TaskDb)
    @Update
    fun updateTask(taskDb: TaskDb)
    @Upsert
    fun upsertTask(taskDb: TaskDb)

    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<TaskDb>

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId ")
    suspend fun getTaskById(taskId: Long): TaskDb

//    @Transaction
//    @Query("SELECT * FROM tasks WHERE categoryId = :categoryId")
//    suspend fun getTasksByCategory(categoryId: Int): CategoriesWithTasks
}