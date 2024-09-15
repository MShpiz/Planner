package com.layka.planner.ComposableFuncs.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.layka.planner.ViewModels.TaskEditViewModel
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditScreen(navController: NavController, id: Long? = null,  taskViewModel: TaskEditViewModel = hiltViewModel()) {

    val context = LocalContext.current // контекст для тоста

    val showToast = fun (text: String) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    val gotData = remember {
        mutableStateOf(false)
    }
    val taskText = remember { mutableStateOf("") }
    val selectedType = remember { mutableStateOf(TaskType.DEFAULT) }
    val selectedCategory = remember { mutableStateOf<Long?>(null) }
    val updateData = fun (t: TaskItem){ // обновление данных полсле получения из бд
        taskText.value = t.taskText
        selectedType.value = t.taskType
        selectedCategory.value = t.category?.categoryId
    }

    if (!gotData.value) { // получение данных из бд должно произойти только 1 раз
           taskViewModel.getTaskInfo(id, updateData)
        gotData.value = true
    }


    val typeMenuExpanded = remember {
        mutableStateOf(false)
    }

    val categoryMenuExpanded = remember {
        mutableStateOf(false)
    }


    val taskTypes = TaskType.entries.toMutableList()
    val typeMenuIcon = if (typeMenuExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    val categoryMenuIcon = if (categoryMenuExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = if (id != null) "Edit Task" else "Create task")}) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TextField( // текст задачи
                value = taskText.value,
                onValueChange = {
                    taskText.value = it
                },
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            )

            // меню выбора типа задачи
            ExposedDropdownMenuBox(
                expanded = typeMenuExpanded.value,
                onExpandedChange = { typeMenuExpanded.value = !typeMenuExpanded.value },
                modifier = Modifier.padding(10.dp)
            ) {
                OutlinedTextField(
                    value = taskTypes[selectedType.value.ordinal].name,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = { Text("Type") },
                    trailingIcon = {
                        Icon(typeMenuIcon, "open types")
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
                                typeMenuExpanded.value = false
                            },
                            text = { Text(text = ttype.name) })
                    }
                }
            }

            // меню выбора категории задачи
            ExposedDropdownMenuBox(
                expanded = categoryMenuExpanded.value,
                onExpandedChange = { categoryMenuExpanded.value = !categoryMenuExpanded.value },
                modifier = Modifier.padding(10.dp)
            ) {
                OutlinedTextField(
                    value = taskViewModel.categoryItems.value[selectedCategory.value] ?: "no category",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = { Text("Category") },
                    trailingIcon = {
                        Icon(categoryMenuIcon, "open categories")
                    }
                )
                ExposedDropdownMenu(
                    expanded = categoryMenuExpanded.value,
                    onDismissRequest = { categoryMenuExpanded.value = false }
                ) {
                    taskViewModel.categoryItems.value.forEach { cat ->
                        DropdownMenuItem(
                            onClick = {
                                selectedCategory.value = cat.key
                                categoryMenuExpanded.value = false
                            },
                            text = { Text(text = cat.value) })
                    }
                }
            }

            Row {
                Button(
                    onClick = { // кнопка сохранения задачи
                    val result = taskViewModel.saveTask(
                        TaskItem(
                            id,
                            taskText = taskText.value,
                            taskType = selectedType.value,
                        ),
                        selectedCategory.value
                    )
                    if (result) {
                        navController.popBackStack()
                    } else {
                        showToast("task is empty")
                    }
                },
                    Modifier.padding(10.dp).weight(1f)
                ) {
                    Text(text = "Save")
                }

                if (id != null) { // если это существующая заадача добавляем кнопку удаления
                    Button(onClick = {
                        if (taskViewModel.deleteTask(id)) {
                            navController.popBackStack()
                        } else {
                            showToast("no such task")
                        }
                    },
                        Modifier.padding(10.dp).weight(1f)) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}