package com.sejin.todo_tdd_clean_architecture.domain.todo

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity
import com.sejin.todo_tdd_clean_architecture.data.repository.ToDoRepository
import com.sejin.todo_tdd_clean_architecture.domain.UseCase

class DeleteAllTodoItemUseCase(private val toDoRepository: ToDoRepository) : UseCase {
    suspend operator fun invoke() {
        return toDoRepository.deleteAll()
    }
}