package com.layka.planner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlannerApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}