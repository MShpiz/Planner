package com.layka.planner.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.layka.planner.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyncViewModel @Inject constructor(private val repository: TaskRepository): ViewModel() {

    fun sync() {
        try {
            viewModelScope.launch { repository.syncDatabase() }
        } catch (e: Exception){
            Log.v("SYNC_ERROR", e.message.toString())
        }

    }
}