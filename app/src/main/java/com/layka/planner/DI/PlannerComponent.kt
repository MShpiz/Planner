package com.layka.planner.DI

import com.layka.planner.entities.TaskDatabase
import com.layka.planner.repository.TaskRepository
import dagger.Component

@Component
interface PlannerComponent {
    fun inject(repository: TaskRepository)

    fun inject(database: TaskDatabase)
}