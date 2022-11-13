package com.sejin.todo_tdd_clean_architecture.data.repository

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity

/**
 * 1. insertToDoList
 * 2. getToDoList
 */
interface ToDoRepository {
    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoList(todoList: List<ToDoEntity>)
}