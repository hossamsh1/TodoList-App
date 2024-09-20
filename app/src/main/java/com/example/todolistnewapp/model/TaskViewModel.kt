package com.example.todolistnewapp.model

import android.app.Application
import android.media.Image
import android.net.Uri
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistnewapp.data.Task
import com.example.todolistnewapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



class TaskViewModel (a:Application) : AndroidViewModel(a){

    val tasks = mutableStateOf<List<Task>>(emptyList())

    val r= TaskRepository(a)



    fun upsertTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            r.insertTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            r.deleteTask(task)
        }
    }


    fun getAllTasks(): Flow<List<Task>> = r.getAllTasks()

}

//class TaskViewModel(a:Application): AndroidViewModel(a) {
//
//    val r= TodoRepository(a)
//
//    fun upsertTasks(todo : Todo){
//        viewModelScope.launch(Dispatchers.IO) {
//            r.upsertTodo(todo)
//        }
//    }
//
//    fun deleteTasks(todo: Todo){
//        viewModelScope.launch(Dispatchers.IO) {
//            r.deleteTodo(todo)
//        }
//    }
//
//    fun getAllTasks(): Flow<List<Todo>> = r.getAllTodo()
//}