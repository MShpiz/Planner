package com.layka.planner.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.data.TaskItem
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(private val repository: TaskRepository): ViewModel() {

    //val task = mutableStateOf(TaskItem(null, ""))

    fun getTaskInfo(id: Long?, updateData: (TaskItem) -> Unit) {
        viewModelScope.launch {
            var res = TaskItem(null, "")
            if (id != null)
                 res = repository.getTaskDetails(id) ?: res
            updateData(res)
        }
    }

//    suspend fun getTaskInfo(id: Long?): TaskItem {
//        if (id == null)
//            return TaskItem(null, "")
//        return repository.getTaskDetails(id) ?: TaskItem(null, "")
//    }

    fun saveTask(task: TaskItem): Boolean {
        if (task.taskText.isBlank()) {
            return false
        }
        viewModelScope.launch {
            repository.saveTask(task)
        }
        return true
    }
}