package com.layka.planner.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.layka.planner.entities.CategoryDb
import com.layka.planner.entities.TaskDb

@Dao
interface TaskCategoryDao {
    @Insert
    fun insertCategory(categoryDb: CategoryDb)
    @Update
    fun updateCategory(categoryDb: CategoryDb)
    @Upsert
    fun upsertCategory(categoryDb: CategoryDb)

    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<CategoryDb>

    @Transaction
    @Query("SELECT * FROM tasks WHERE :categoryId = categoryId")
    suspend fun getCategoryById(categoryId: Long): CategoryDb
}