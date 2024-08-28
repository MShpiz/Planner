package com.layka.planner.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.data.TaskItem
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FullListViewModel @Inject constructor (private val repository: TaskRepository): ViewModel() {
    val tasks = mutableStateOf<List<TaskItem>>(listOf())
    init {
        getTasks()
    }

    fun getTasks() {
       viewModelScope.launch {
           tasks.value = repository.getAllTasks()
       }
    }
}