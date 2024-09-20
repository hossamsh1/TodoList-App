package com.example.todolistnewapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val task: String,
    var isCompleted: Boolean= false,
  //  val image: String
)