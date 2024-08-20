package com.layka.planner.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.layka.planner.data.TaskType

@Entity(tableName = "tasks")
class TaskDb (
    @PrimaryKey
    @ColumnInfo(name="taskId")
    val id:Long,

    @ColumnInfo(name="taskText")
    val text: String,

    @ColumnInfo(name="isDone")
    val isDone: Boolean,

    @ColumnInfo(name="taskType")
    val type: TaskType,

    @ColumnInfo(name="categoryId")
    val categoryId: Long?
)

