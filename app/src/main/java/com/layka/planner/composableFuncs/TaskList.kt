package com.layka.planner.composableFuncs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.layka.planner.R
import com.layka.planner.ViewModels.ListViewModel
import com.layka.planner.composableFuncs.cards.DailyCard
import com.layka.planner.composableFuncs.cards.PlainCard
import com.layka.planner.composableFuncs.cards.WeeklyCard
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType
import java.time.LocalDate

@Composable
fun TaskList(
    navController: NavController,
    taskProgressCallback: ((Float) -> Unit)? = null,
    type: TaskType? = null,
    taskViewModel: ListViewModel = hiltViewModel(),
    catId: Long? = null,
    onlyUncategorized: Boolean = false
) {
    val context = LocalContext.current // контекст для тоста

    val showToast = fun(text: String?) {
        Toast.makeText(
            context,
            text ?: "error",
            Toast.LENGTH_SHORT
        ).show()
    }
    val painter = painterResource(id = R.drawable.plus)

    taskViewModel.getTasks(type, catId)
    if (taskProgressCallback != null) {
        taskViewModel.getTaskProgress(taskProgressCallback)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (type == null && catId == null && !onlyUncategorized) {
            Row {
                Text(taskViewModel.syncResult.value, Modifier.weight(1f))
                Button(
                    onClick = {
                        taskViewModel.sync(showToast)
                    },
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    Text(stringResource(id = R.string.sync))
                }
            }
        }

        if (taskViewModel.tasks.value.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
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
                            //Log.v("ClickTrack", "clicked box of item ${it.id}")
                            navController.navigate("edit_task/${it.id}")
                        }
                    ) {
                        val updateChecked = fun() {
                            //Log.v("ClickTrack", "${it.id} ${taskViewModel.tasks.value[index].isDone} ${it.taskText}")
                            taskViewModel.tasks.value[index].isDone =
                                !taskViewModel.tasks.value[index].isDone

                            if (taskViewModel.tasks.value[index].isDone)
                                taskViewModel.tasks.value[index].doneDate = LocalDate.now()
                            else
                                taskViewModel.tasks.value[index].doneDate = null

                            //Log.v("ClickTrack", taskViewModel.tasks.value[index].doneDate.toString())
                            taskViewModel.updateTask(taskViewModel.tasks.value[index])
                            if (taskProgressCallback != null)
                                taskViewModel.getTaskProgress(taskProgressCallback)
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
}