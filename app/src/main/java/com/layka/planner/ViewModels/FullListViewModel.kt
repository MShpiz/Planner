package com.layka.planner.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.data.TaskItem
import com.layka.planner.data.TaskType
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FullListViewModel @Inject constructor (private val repository: TaskRepository): ViewModel() {
    val tasks = mutableStateOf<List<TaskItem>>(listOf())

    fun getTasks(type: TaskType? = null) {
       viewModelScope.launch {
           tasks.value = if (type == null){
               repository.getAllTasks()
           } else {
               repository.getAllTasksByType(type)
           }
           tasks.value = tasks.value.sortedBy { it.isDone }
       }
    }

    fun updateTask(taskItem: TaskItem) {
        viewModelScope.launch {
            repository.saveTask(taskItem)
        }

    }

    fun sync() {
        try {
            viewModelScope.launch {
                repository.syncDatabase()
            }
        } catch (e: Exception){
            Log.v("SYNC_ERROR", e.message.toString())
        }

    }

    fun getTaskProgress(taskProgressCallback: (Float) -> Unit) {
        taskProgressCallback((tasks.value.count { it.isDone }.toFloat()/tasks.value.count()))
    }
}