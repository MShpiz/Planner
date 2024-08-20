package com.layka.planner.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="TaskCategories")
class CategoryDb(
    @PrimaryKey
    @ColumnInfo("categoryId")
    val id: Long,

    @ColumnInfo("categoryName")
    val name: String,

    @ColumnInfo("categoryBackgroundColor")
    val backgroundColor: ULong,

    @ColumnInfo("categoryTagColor")
    val tagColor: ULong
)