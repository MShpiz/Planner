package com.layka.planner.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: TaskRepository): ViewModel() {
    val categoryItems = mutableStateOf(mapOf<Long?, String>())

    init {
        viewModelScope.launch {
            val res = mutableMapOf<Long?, String>()
            val categories = repository.getAllCategories()
            categories.map { res[it.categoryId] = it.categoryName }
            categoryItems.value = res
        }
    }
}
