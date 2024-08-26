package com.layka.planner.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.layka.planner.dao.TaskCategoryDao
import com.layka.planner.dao.TaskDao
import com.layka.planner.repository.DatabaseAPI

@Database(
    entities = [
        TaskDb::class,
        CategoryDb::class,
        TaskCategoryCrossRef::class
    ],
    version = 1
)
abstract class TaskDatabase: RoomDatabase(), DatabaseAPI {
    abstract override fun taskDao(): TaskDao

    abstract override fun taskCategoryDao(): TaskCategoryDao
}