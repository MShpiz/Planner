package com.layka.planner.entities.typeConverters

import android.util.Log
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


class DateConverter {
    private val zoneId = ZoneId.of("Europe/Moscow")
    @TypeConverter
    fun toDate(value: Long): LocalDate {
        //Log.v("Update", taskItem.toString())
        return Instant.ofEpochSecond(value).atZone(zoneId).toLocalDate()
    }

    @TypeConverter
    fun fromDate(date: LocalDate): Long {
        return date.atStartOfDay(zoneId).toEpochSecond()
    }
}