package com.sejin.todo_tdd_clean_architecture.presentation.detail

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity

sealed class ToDoDetailState {
    object UnInitialized : ToDoDetailState()

    object Loading : ToDoDetailState()

    data class Success(val toDoItem: ToDoEntity) : ToDoDetailState()

    object Delete: ToDoDetailState()

    object Modify: ToDoDetailState()

    object Error: ToDoDetailState()

    object Write: ToDoDetailState()
}
