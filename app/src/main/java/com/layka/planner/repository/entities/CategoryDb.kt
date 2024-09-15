package com.layka.planner.repository.entities

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.layka.planner.repository.entities.typeConverters.ColorConverters


@TypeConverters(ColorConverters::class)
@Entity(tableName ="TaskCategories")
data class CategoryDb(
    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("tagColor")
    val tagColor: Color,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0
)