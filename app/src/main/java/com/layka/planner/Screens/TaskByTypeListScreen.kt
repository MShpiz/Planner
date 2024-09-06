package com.layka.planner.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.layka.planner.cards.TaskList
import com.layka.planner.data.TaskType

@Composable
fun TaskByTypeListScreen(navController: NavController, type: Int) {
    val progress = remember{
        mutableFloatStateOf(0.0f)
    }

    val updateProgress = fun (newValue: Float){
        progress.value = newValue
    }

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
            Row{
                Text(text = "Daily tasks completion")
                LinearProgressIndicator(
                    progress = { progress.floatValue },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            TaskList(
                navController = navController,
                taskProgressCallback = updateProgress,
                type = TaskType.entries[type%TaskType.entries.size]
            )
        }
    }
}