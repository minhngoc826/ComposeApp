package com.example.composeapp.basic.lazylist

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.composeapp.common.Logger
import kotlinx.coroutines.flow.MutableStateFlow

class TaskViewModel() : ViewModel() {

    // way 1
//    private val _tasks1 = getListTasks().toMutableStateList()                        //
//    private val _tasks = _tasks1
    // way 2
//    private val _tasks2 = mutableListOf<Task>().apply { addAll(getListTasks()) }    //
//    private val _tasks = _tasks2.toMutableStateList()
    // way 3
    private val _tasks3 = MutableStateFlow(getListTasks())              //
    private val _tasks = _tasks3.value.toMutableStateList()

    val tasks: List<Task>
        get() = _tasks

    fun removeTask(task: Task) {
        Logger.d(TAG, "removeTask: task: $task - size: ${tasks.size}")
        _tasks.remove(task)
    }

    companion object {
        private const val TAG = "TaskViewModel"
    }
}

private fun getListTasks() = List(30) { i -> Task(i, "Task # $i") }

data class Task(val id: Int, val title: String)