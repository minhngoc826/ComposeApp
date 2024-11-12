package com.example.composeapp.basic.lazylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BasicLazyListScreen(
    taskViewModel: TaskViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TaskCounter(modifier)

        TaskList(taskViewModel.tasks, modifier = modifier.weight(1f), onCloseTask = { task -> taskViewModel.removeTask(task) })
    }
}

@Composable
fun TaskCounter(modifier: Modifier) {
    Column(modifier = Modifier.fillMaxWidth().background(color = androidx.compose.ui.graphics.Color.LightGray).padding(start = 16.dp, end = 10.dp, top = 10.dp)) {
        var count by rememberSaveable { mutableIntStateOf(0) } //

        Text(text = "You have $count tasks to do today!")
        Button(onClick = { count++ }, modifier = modifier.padding(8.dp)) {
            Text(text = "Add a task")
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>, modifier: Modifier, onCloseTask: (Task) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(items = tasks, key = { task -> task.id }) {
            task -> TaskItem(task.title, modifier, onClose = { onCloseTask(task) }
            )
        }
    }
}

@Composable
fun TaskItem(taskName: String, modifier: Modifier, onClose: () -> Unit) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 16.dp, top = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = taskName, modifier.weight(1f))
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checkedState = it }
        )

        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
        }
    }
}
