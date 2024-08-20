package com.layka.planner.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.layka.planner.entities.TaskDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PlannerModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : TaskDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            TaskDatabase::class.java, "plannerDB"
        ).build()
    }
}