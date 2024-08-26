package com.layka.planner.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.layka.planner.data.TaskType
import com.layka.planner.entities.typeConverters.TaskTypeConverters

@TypeConverters(TaskTypeConverters::class)
@Entity(tableName = "tasks")
data class TaskDb (
    @ColumnInfo(name="taskText")
    var text: String,

    @ColumnInfo(name="isDone")
    var isDone: Boolean,

    @ColumnInfo(name="taskType")
    var type: TaskType,

    @ColumnInfo(name="categoryId")
    var categoryId: Int?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="taskId")
    var id:Long = 0
)


