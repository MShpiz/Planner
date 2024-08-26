package com.layka.planner.DI

import android.content.Context
import androidx.room.Room
import com.layka.planner.entities.TaskDatabase
// import com.layka.planner.entities.typeConverters.ColorConverters
import com.layka.planner.entities.typeConverters.TaskTypeConverters
import com.layka.planner.repository.TaskRepository
import com.layka.planner.repository.DatabaseAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlannerModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : DatabaseAPI {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java, "plannerDB"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: DatabaseAPI) : TaskRepository {
        return TaskRepository(database)
    }
}