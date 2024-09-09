package com.layka.planner.entities.typeConverters

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun toDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
}