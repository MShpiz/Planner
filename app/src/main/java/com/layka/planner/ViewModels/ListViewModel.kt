package com.layka.planner.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor (private val repository: TaskRepository): ViewModel() {
    val tasks = mutableStateOf(listOf<TaskItem>())

    fun getTasks(type: TaskType? = null) {
        viewModelScope.launch {
            tasks.value =
           if (type == null){
               repository.getAllTasks().sortedBy { it.isDone }
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