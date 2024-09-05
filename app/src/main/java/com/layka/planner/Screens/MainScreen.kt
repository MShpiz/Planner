package com.layka.planner.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.layka.planner.ViewModels.SyncViewModel
import com.layka.planner.cards.TaskList

@Composable
fun MainScreen(navController: NavController, viewModel: SyncViewModel = hiltViewModel()) {
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

            val onDataRefresh = {}
            Button(onClick = { viewModel.sync()}) {
                Text("Synchronize")
            }
            TaskList(navController = navController)
        }
    }
}