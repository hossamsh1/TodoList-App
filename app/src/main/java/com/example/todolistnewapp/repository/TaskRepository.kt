package com.example.todolistnewapp.repository

import android.content.Context
import com.example.todolistnewapp.data.Task
import com.example.todolistnewapp.data.TaskDao
import com.example.todolistnewapp.data.TaskDatabase
import kotlinx.coroutines.flow.Flow

class TaskRepository(context: Context) {

    val db = TaskDatabase.getDatabase(context)

     fun getAllTasks(): Flow<List<Task>> = db.dao().getAllTasks()

    suspend fun insertTask(task: Task) = db.dao().upsertTask(task)

    suspend fun updateTask(task: Task) = db.dao().upsertTask(task)

    suspend fun deleteTask(task: Task) = db.dao().deleteTask(task)
}


//class TodoRepository(context: Context) {
//
//    val db = TodoDatabase.getDataBase(context)
//
//    suspend fun upsertTodo(todo : Todo){
//        db.todoDAO().addTask(todo)
//    }
//
//    suspend fun deleteTodo(todo: Todo){
//        db.todoDAO().deleteTask(todo)
//    }
//
//    fun getAllTodo() : Flow<List<Todo>> = db.todoDAO().getTask()
//}