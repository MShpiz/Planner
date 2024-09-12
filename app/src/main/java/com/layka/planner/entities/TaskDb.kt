package com.layka.planner.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.layka.planner.data.TaskType
import com.layka.planner.entities.typeConverters.DateConverter
import com.layka.planner.entities.typeConverters.TaskTypeConverters
import java.time.LocalDate

@TypeConverters(TaskTypeConverters::class, DateConverter::class)
@Entity(tableName = "tasks")
data class TaskDb (
    @ColumnInfo(name="taskText")
    var text: String,

    @ColumnInfo(name="isDone")
    var isDone: Boolean,

    @ColumnInfo(name="taskType")
    var type: TaskType,

    @ColumnInfo(name="categoryId")
    var categoryId: Long?,

    @ColumnInfo(name="dateDone")
    var dateDone: LocalDate?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="taskId")
    var id:Long = 0
)


