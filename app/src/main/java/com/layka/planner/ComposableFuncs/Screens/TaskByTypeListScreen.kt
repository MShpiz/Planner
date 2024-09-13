package com.layka.planner.ComposableFuncs.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.layka.planner.ComposableFuncs.TaskList
import com.layka.planner.data.TaskType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskByTypeListScreen(navController: NavController, type: Int? = null) {
    val progress = remember{
        mutableFloatStateOf(0.0f)
    }

    val updateProgress = fun (newValue: Float){
        progress.value = newValue
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    NavigationDrawerItem(
                        label = {Text("All Tasks")},
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("main_screen")
                                  },
                    )
                    for (i in TaskType.entries) {
                        NavigationDrawerItem(
                            label = {Text(i.name)},
                            selected = false,
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate("/task_list/${i.ordinal}")
                            },
                        )
                    }
                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = {Text("create category")},
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("create_category")
                        }
                    )
                    /*category list*/

                }
            }
        },
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("create_note") },
                ) {
                    Icon(Icons.Filled.Add, "Add task")
                }
            },
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("")
                    },
                    navigationIcon = {
                        IconButton(onClick = { if (drawerState.isClosed) scope.launch { drawerState.open()} else scope.launch { drawerState.close()} }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "menu"
                            )

                        }
                    },
                )
            }
        )
        { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                if (type != null) {
                    Column {
                        Text(text = "${TaskType.entries[type].name} tasks completion")
                        LinearProgressIndicator(
                            progress = { progress.floatValue },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    TaskList(
                        navController = navController,
                        taskProgressCallback = updateProgress,
                        type = TaskType.entries[type % TaskType.entries.size]
                    )
                } else {
                    TaskList(navController = navController)
                }
            }
        }
    }
}