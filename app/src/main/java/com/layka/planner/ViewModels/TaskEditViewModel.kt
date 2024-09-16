package com.layka.planner.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.data.TaskCategory
import com.layka.planner.data.TaskItem
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    val categoryItems = mutableStateOf(mapOf<Long?, String>(null to "no category"))
    private val categories = mutableStateOf(listOf<TaskCategory>())

    init {
        viewModelScope.launch {
            val res = mutableMapOf<Long?, String>(null to "no category")
            categories.value = repository.getAllCategories()
            categories.value.map { res[it.categoryId] = it.categoryName }
            categoryItems.value = res
        }
    }

    fun getTaskInfo(id: Long?, updateData: (TaskItem) -> Unit) {
        viewModelScope.launch {
            var res = TaskItem(null, "")
            if (id != null) res = repository.getTaskDetails(id) ?: res
            updateData(res)
        }
    }

    fun saveTask(task: TaskItem, selectedCategory: Long?): Boolean {
        if (task.taskText.isBlank()) {
            return false
        }
        task.category = categories.value.find { it.categoryId == selectedCategory }
        viewModelScope.launch {
            repository.saveTask(task)
        }
        return true
    }

    fun deleteTask(id: Long?): Boolean {
        if (id == null) {
            return false
        }
        viewModelScope.launch {
            repository.deleteTask(id)
        }
        return true
    }
}