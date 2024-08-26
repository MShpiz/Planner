package com.layka.planner.entities.typeConverters


import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter

class ColorConverters {
    @TypeConverter
    fun toColor(value: Int): Color {
        return Color(value)
    }

    @TypeConverter
    fun fromColor(color: Color): Int {
        return color.value.toInt()
    }
}