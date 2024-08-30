package com.layka.planner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.layka.planner.Screens.MainScreen
import com.layka.planner.Screens.TaskEditScreen
import com.layka.planner.cards.TaskList
import com.layka.planner.ui.theme.PlannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlannerTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main_screen") {
                    composable("main_screen") {
                        MainScreen(navController)
                    }

                    composable(
                        "create_note") {
                        TaskEditScreen(navController)
                    }

                    composable(
                        "edit_task/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.LongType
                                nullable = false
                            }
                        )
                    ) {
                        val id = remember {
                            it.arguments?.getLong("id")
                        }
                        TaskEditScreen(navController, id = id)
                    }
                }

            }
        }
    }
}