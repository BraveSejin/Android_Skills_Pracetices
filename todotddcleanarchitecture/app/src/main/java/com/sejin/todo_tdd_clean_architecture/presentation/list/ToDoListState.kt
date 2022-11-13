package com.sejin.todo_tdd_clean_architecture.presentation.list

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity

sealed class ToDoListState {
    object UnInitialized: ToDoListState()

    object Loading: ToDoListState()

    data class Success(
        val toDoList: List<ToDoEntity>
    ): ToDoListState()

    object Error: ToDoListState()
}