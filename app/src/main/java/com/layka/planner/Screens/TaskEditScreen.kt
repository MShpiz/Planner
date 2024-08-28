package com.layka.planner.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.layka.planner.ViewModels.TaskEditViewModel
import com.layka.planner.data.TaskType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditScreen(navController: NavController, id: Int? = null,  taskViewModel: TaskEditViewModel = hiltViewModel()) {
        if (id != null) {
        taskViewModel.getTaskInfo(id)
    }

    val typeMenuExpanded = remember {
        mutableStateOf(false)
    }
    val taskText = remember { mutableStateOf(taskViewModel.task.value.taskText) }
    val selectedType = remember { mutableStateOf(taskViewModel.task.value.taskType) }
    val taskTypes = TaskType.entries.toMutableList()
    val icon = if (typeMenuExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(
                value = taskText.value,
                onValueChange = { taskText.value = it },
                placeholder = { "Task" }
            )

            ExposedDropdownMenuBox(
                expanded = typeMenuExpanded.value,
                onExpandedChange = { typeMenuExpanded.value = !typeMenuExpanded.value }
            ) {
                OutlinedTextField(
                    value = taskTypes[selectedType.value.ordinal].name,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.menuAnchor(),
                    label = { Text("Type") },
                    trailingIcon = {
                        Icon(icon, "contentDescription")
                    }
                )
                ExposedDropdownMenu(
                    expanded = typeMenuExpanded.value,
                    onDismissRequest = { typeMenuExpanded.value = false }
                ) {
                    taskTypes.forEach { ttype ->
                        DropdownMenuItem(
                            onClick = {
                                selectedType.value = ttype
                            },
                            text = { Text(text = ttype.name) })
                    }
                }

            }
            Button(onClick = {
                taskViewModel.task.value.taskText = taskText.value
                taskViewModel.task.value.taskType = selectedType.value
                taskViewModel.saveTask()
                navController.popBackStack()
            }) {
                Text(text = "Save")
            }
        }
    }
}