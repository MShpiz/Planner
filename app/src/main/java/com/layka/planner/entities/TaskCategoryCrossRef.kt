package com.layka.planner.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["id", "categoryId"])
data class TaskCategoryCrossRef (
    var id: Int,
    var categoryId: Int
)

class CategoriesWithTasks(
    @Embedded var category: CategoryDb,

    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId",
        associateBy = Junction(TaskCategoryCrossRef::class)
    )
    var tasks: List<TaskDb>
)