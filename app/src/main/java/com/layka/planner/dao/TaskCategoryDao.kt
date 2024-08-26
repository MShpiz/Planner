package com.layka.planner.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import androidx.room.Update
import androidx.room.Upsert
import com.layka.planner.entities.CategoryDb

@Dao
interface TaskCategoryDao {
    @Insert
    fun insertCategory(categoryDb: CategoryDb)
    @Update
    fun updateCategory(categoryDb: CategoryDb)
    @Upsert
    fun upsertCategory(categoryDb: CategoryDb)

    @Query("SELECT * FROM TaskCategories")
    suspend fun getAll(): List<CategoryDb>

    @Transaction
    @Query("SELECT * FROM TaskCategories WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: Long): CategoryDb
}