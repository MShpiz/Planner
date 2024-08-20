package com.layka.planner.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.layka.planner.dao.TaskCategoryDao
import com.layka.planner.dao.TaskDao

@Database(
    entities = [
        TaskDb::class,
        CategoryDb::class
    ],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    abstract fun taskCategoryDao(): TaskCategoryDao
}