package com.layka.planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.layka.planner.cards.TaskList
import com.layka.planner.cards.TaskListPreview
import com.layka.planner.data.TaskCategory
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType
import com.layka.planner.ui.theme.PlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            PlannerTheme {
                TaskListPreview()
            }
        }
    }
}