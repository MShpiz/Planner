package com.layka.planner.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor (private val repository: TaskRepository): ViewModel() {
    val tasks = mutableStateOf(listOf<TaskItem>())

    val unCheck = fun (item: TaskItem):TaskItem {

        Log.v("GET_TASKS", "${item} ${LocalDate.now()}")

        if (item.taskType == TaskType.DAILY && item.doneDate != null && item.doneDate != LocalDate.now()){
            item.doneDate = null
            item.isDone = false
            updateTask(item)
        } else if (item.taskType == TaskType.WEEKLY && item.doneDate != null) {
            val weekField =  WeekFields.of(Locale.getDefault())
            val weekNumber = item.doneDate!!.get(weekField.weekOfYear())
            val currWeekNumber = LocalDate.now().get(weekField.weekOfYear())
            if (weekNumber != currWeekNumber) {
                item.doneDate = null
                item.isDone = false
                updateTask(item)
            }
        }
        return item
    }

    fun getTasks(type: TaskType? = null) {
        viewModelScope.launch {
            tasks.value =
           if (type == null){
               repository.getAllTasks().map { unCheck(it) }.sortedBy { it.isDone }
           } else {
               repository.getAllTasksByType(type).sortedBy { it.isDone }
           }
           Log.v("GET_TASKS", tasks.value.size.toString())
        }

    }

    fun updateTask(taskItem: TaskItem) {
        viewModelScope.launch {
            repository.saveTask(taskItem)
            delay(500L)
            //tasks.value = tasks.value.sortedBy { it.isDone }
        }
    }

    fun sync() {
        try {
            viewModelScope.launch {
                repository.syncDatabase()
                getTasks()
            }
        } catch (e: Exception){
            Log.v("SYNC_ERROR", e.message.toString())
        }
    }

    fun getTaskProgress(taskProgressCallback: (Float) -> Unit) {
        taskProgressCallback((tasks.value.count { it.isDone }.toFloat()/tasks.value.size))
    }
}