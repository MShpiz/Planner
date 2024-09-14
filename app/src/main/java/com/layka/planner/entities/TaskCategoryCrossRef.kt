package com.layka.planner.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

//@Entity(primaryKeys = ["id", "categoryId"])
//data class TaskCategoryCrossRef (
//    var id: Long,
//    var categoryId: Long?
//)

class CategoriesWithTasks(
    @Embedded val category: CategoryDb,

    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val tasks: List<TaskDb>
)