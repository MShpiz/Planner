package com.layka.planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.layka.planner.Screens.MainScreen
import com.layka.planner.Screens.TaskEditScreen
import com.layka.planner.Screens.TaskByTypeListScreen
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

                    composable("/task_list/{type}",
                        arguments = listOf(
                            navArgument("type"){
                                type = NavType.IntType
                                nullable = false
                            }
                        )
                    ) {
                        val type = remember {
                            it.arguments?.getInt("type")
                        }
                        TaskByTypeListScreen(navController = navController, type = type ?: 0)
                    }
                }

            }
        }
    }
}