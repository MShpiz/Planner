package com.layka.planner.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["taskId", "categoryId"])
data class TaskCategoryCrossRef (
    val taskId: Long,
    val categoryId: Long
)

class CategoriesWithTasks(
    @Embedded val category: TaskDb,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId",
        associateBy = Junction(TaskCategoryCrossRef::class)
    )
    val tasks: List<CategoryDb>
)