package com.layka.planner.repository

import com.layka.planner.dao.TaskCategoryDao
import com.layka.planner.dao.TaskDao

interface DatabaseAPI {
    fun taskDao(): TaskDao

    fun taskCategoryDao(): TaskCategoryDao
}