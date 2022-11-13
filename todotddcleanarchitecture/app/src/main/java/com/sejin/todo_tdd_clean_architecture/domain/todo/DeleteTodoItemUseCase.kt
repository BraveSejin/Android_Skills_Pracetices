package com.sejin.todo_tdd_clean_architecture.domain.todo

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity
import com.sejin.todo_tdd_clean_architecture.data.repository.ToDoRepository
import com.sejin.todo_tdd_clean_architecture.domain.UseCase

class DeleteTodoItemUseCase(private val toDoRepository: ToDoRepository) : UseCase {
    suspend operator fun invoke(id: Long): Boolean {
        return toDoRepository.deleteTodoItem(id)
    }
}