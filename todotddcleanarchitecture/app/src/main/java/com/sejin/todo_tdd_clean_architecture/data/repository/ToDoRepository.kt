package com.sejin.todo_tdd_clean_architecture.data.repository

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity

/**
 * 1. insertToDoList
 * 2. getToDoList
 * 3. updateToDoItem
 */
interface ToDoRepository {
    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun getToDoItem(id: Long): ToDoEntity?

    suspend fun insertToDoItem(toDoEntity: ToDoEntity): Long

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)

    suspend fun updateToDoItem(toDoEntity: ToDoEntity)

    suspend fun deleteToDoItem(id: Long)

    suspend fun deleteAll()
}