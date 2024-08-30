package com.layka.planner.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.layka.planner.cards.TaskList

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("create_note")},
            ) {
                Icon(Icons.Filled.Add, "Add task")
            }
        }
    )
    { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            TaskList(navController = navController)
        }
    }
}