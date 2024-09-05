package com.layka.planner.cards

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.layka.planner.R
import com.layka.planner.ViewModels.FullListViewModel
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType

@Composable
fun  TaskList(taskViewModel: FullListViewModel = hiltViewModel(), navController: NavController) {
    val painter = painterResource(id = R.drawable.plus)
    taskViewModel.getTasks()
    if (taskViewModel.tasks.value.isEmpty()) {
        Column( horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()){
            Image(painter, "no tasks")
            Text(text = stringResource(id = R.string.no_tasks))
        }
        
    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

            itemsIndexed(taskViewModel.tasks.value) { index: Int, it: TaskItem ->
                Box(
                    modifier = Modifier.clickable {
                        Log.v("ClickTrack", "clicked box of item ${it.id}")
                        navController.navigate("edit_task/${it.id}")
                    }
                ) {
                    val updateChecked = fun() {
                        it.isDone = !it.isDone
                        taskViewModel.updateTask(it)
                    }
                    when (it.taskType) {
                        TaskType.DAILY ->
                            DailyCard(it, updateChecked)

                        TaskType.WEEKLY ->
                            WeeklyCard(it, updateChecked)

                        TaskType.DEFAULT -> {
                            PlainCard(it, updateChecked)
                        }
                    }
                }
            }
        }
    }
}


