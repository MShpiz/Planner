package com.layka.planner.entities.typeConverters

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.layka.planner.data.TaskType

class TaskTypeConverters {
    @TypeConverter
    fun toTaskType(value: Int): TaskType {
        return TaskType.entries[value]
    }

    @TypeConverter
    fun fromTaskType(type: TaskType): Int {
        return type.ordinal
    }


}
