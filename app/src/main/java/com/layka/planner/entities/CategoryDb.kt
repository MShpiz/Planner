package com.layka.planner.entities

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.layka.planner.entities.typeConverters.ColorConverters


@TypeConverters(ColorConverters::class)
@Entity(tableName ="TaskCategories")
data class CategoryDb(
    @PrimaryKey
    @ColumnInfo("id")
    var id: Long,

    @ColumnInfo("name")
    var name: String,

    @ColumnInfo("bgColor")
    var backgroundColor: Color,

    @ColumnInfo("tagColor")
    var tagColor: Color
)