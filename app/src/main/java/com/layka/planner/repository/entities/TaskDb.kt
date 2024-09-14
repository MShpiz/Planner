package com.layka.planner.repository.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.layka.planner.data.TaskType
import com.layka.planner.repository.entities.typeConverters.DateConverter
import com.layka.planner.repository.entities.typeConverters.TaskTypeConverters
import java.time.LocalDate

@TypeConverters(TaskTypeConverters::class, DateConverter::class)
@Entity(tableName = "tasks")
data class TaskDb (
    @ColumnInfo(name="taskText")
    val text: String,

    @ColumnInfo(name="isDone")
    val isDone: Boolean,

    @ColumnInfo(name="taskType")
    val type: TaskType,

    @ColumnInfo(name="categoryId")
    val categoryId: Long?,

    @ColumnInfo(name="dateDone")
    val dateDone: LocalDate?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="taskId")
    val id:Long = 0
)


