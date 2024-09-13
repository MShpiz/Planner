package com.layka.planner.entities.typeConverters


import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter

class ColorConverters {
    @TypeConverter
    fun toColor(value: Long): Color {
        return Color(value.toULong())
    }

    @TypeConverter
    fun fromColor(color: Color): Long {
        Log.v("CAT_VAL", "color ${color.value} ${color.value.toLong()}")
        return color.value.toLong()
    }
}