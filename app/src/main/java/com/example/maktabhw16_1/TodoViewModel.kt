package com.example.maktabhw16_1

import android.icu.text.Transliterator.Position
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel :ViewModel() {
    private val _taskLiveData = MutableLiveData<Task>()
    val taskLiveData: LiveData<Task> = _taskLiveData

    private val _positionLiveData = MutableLiveData<Int>()
    val positionLiveData: LiveData<Int> = _positionLiveData

    fun updateTask(task: Task){
        _taskLiveData.value=task
    }

    fun updatePositionTask(position: Int){
        _positionLiveData.value=position
    }
}