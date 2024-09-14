package com.layka.planner.repository

import com.layka.planner.repository.dao.TaskCategoryDao
import com.layka.planner.repository.dao.TaskDao

interface DatabaseAPI {
    fun taskDao(): TaskDao

    fun taskCategoryDao(): TaskCategoryDao
}