package com.layka.planner.composableFuncs.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.layka.planner.R
import com.layka.planner.ViewModels.MainScreenViewModel
import com.layka.planner.composableFuncs.TaskList
import com.layka.planner.data.TaskType
import kotlinx.coroutines.launch

@Composable
fun CategoryDrawerLabel(
    it: Map.Entry<Long?, String>,
    navController: NavController,
    viewModel: MainScreenViewModel
) {
    val expanded = remember { mutableStateOf(false) }
    val isNotDeleted = remember {
        mutableStateOf(true)
    }

    if (isNotDeleted.value) Row(Modifier.fillMaxWidth()) {
        Text(
            text = it.value,
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        Box {
            IconButton(onClick = { expanded.value = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "show menu")
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                Modifier.align(Alignment.CenterEnd)
            ) {
                Text(stringResource(id = R.string.edit),
                    modifier = Modifier
                        .clickable { navController.navigate("edit_category/${it.key}") }
                        .padding(vertical = 5.dp, horizontal = 10.dp))
                Text(stringResource(id = R.string.delete), modifier = Modifier
                    .clickable {
                        isNotDeleted.value = false
                        viewModel.deleteCategory(it.key)
                    }
                    .padding(vertical = 5.dp, horizontal = 10.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskByTypeListScreen(
    navController: NavController,
    type: Int? = null,
    viewModel: MainScreenViewModel = hiltViewModel(),
    catId: Long? = null
) {
    val screenTitle: String = if (type != null && type < TaskType.entries.size) {
        when (TaskType.entries[type]) {
            TaskType.DAILY -> stringResource(id = R.string.daily_tag_text)
            TaskType.WEEKLY -> stringResource(id = R.string.weekly_tag_text)
            TaskType.DEFAULT -> stringResource(id = R.string.non_repeating)
        }
    } else if (catId != null && catId < viewModel.categoryItems.value.size) {
        viewModel.categoryItems.value[catId]!!
    } else {
        stringResource(id = R.string.all_tasks)
    }


    val progress = remember {
        mutableFloatStateOf(0.0f)
    }

    val updateProgress = fun(newValue: Float) {
        progress.floatValue = newValue
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    NavigationDrawerItem(
                        label = { Text(stringResource(id = R.string.all_tasks)) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("main_screen")
                        },
                    )
                    for (i in TaskType.entries) {
                        NavigationDrawerItem(
                            label = { Text(i.name) },
                            selected = false,
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate("/task_list/${i.ordinal}")
                            },
                        )
                    }
                    HorizontalDivider()

                    viewModel.categoryItems.value.forEach {
                        NavigationDrawerItem(label = {
                            CategoryDrawerLabel(
                                it,
                                navController,
                                viewModel
                            )
                        },
                            selected = false,
                            onClick = {
                                navController.navigate("/category/${it.key}")
                            })
                    }

                    NavigationDrawerItem(label = { Text(stringResource(id = R.string.create_category)) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("create_category")
                        },
                        icon = {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.create_category)
                            )
                        })

                }
            }
        },
    ) {
        Scaffold(floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("create_note") },
            ) {
                Icon(Icons.Filled.Add, "Add task")
            }
        }, topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(screenTitle)
                },
                navigationIcon = {
                    IconButton(onClick = { if (drawerState.isClosed) scope.launch { drawerState.open() } else scope.launch { drawerState.close() } }) {
                        Icon(
                            imageVector = Icons.Filled.Menu, contentDescription = "menu"
                        )

                    }
                },
            )
        }) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                if (type != null) {
                    Column {
                        LinearProgressIndicator(
                            progress = { progress.floatValue },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp, horizontal = 5.dp)
                                .clip(
                                    RoundedCornerShape(10.dp)
                                )
                                .height(20.dp),
                        )
                    }
                    TaskList(
                        navController = navController,
                        taskProgressCallback = updateProgress,
                        type = TaskType.entries[type % TaskType.entries.size]
                    )
                } else if (catId != null) {
                    TaskList(
                        navController = navController, catId = catId
                    )
                } else {
                    TaskList(navController = navController)
                }
            }
        }
    }
}