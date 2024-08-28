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
class TaskEditViewModel @Inject constructor(private val repository: TaskRepository): ViewModel() {
    val task = mutableStateOf(TaskItem(""))

    fun getTaskInfo(id: Int){
        viewModelScope.launch {
            val res = repository.getTaskDetails(id)
            if (res != null) {
                task.value = res
            }
        }
    }

    fun saveTask(): Boolean {
        if (task.value.taskText.isEmpty()) {
            return false
        }
        viewModelScope.launch {
            repository.saveNewTask(task.value)
        }
        return true
    }
}