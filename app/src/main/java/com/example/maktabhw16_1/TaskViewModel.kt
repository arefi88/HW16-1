package com.example.maktabhw16_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val _taskLiveData = MutableLiveData<Task>()
    val taskLiveData: LiveData<Task> = _taskLiveData

    fun updateTask(task: Task){
        _taskLiveData.value=task
    }
}