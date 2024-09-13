package com.layka.planner.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.data.TaskCategory
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoryViewModel @Inject constructor(private val repository: TaskRepository): ViewModel() {
    fun getCategory(id: Long?, updateData: (TaskCategory) -> Unit) {
        viewModelScope.launch {
            var res = TaskCategory(null, "")
            if (id != null)
                res = repository.getCategoryDetails(id) ?: res
            updateData(res)
        }
    }

    fun save(taskCategory: TaskCategory): Boolean {
        if (taskCategory.categoryName.isBlank()){
            return false
        }
        viewModelScope.launch {
            Log.v("SAVE_CATEGORY", taskCategory.toString())
            repository.saveCategory(taskCategory)
        }
        return true
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            repository.deleteCategory(id)
        }
    }
}
