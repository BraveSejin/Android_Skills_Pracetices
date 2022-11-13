package com.sejin.todo_tdd_clean_architecture.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity
import com.sejin.todo_tdd_clean_architecture.domain.todo.DeleteAllTodoItemUseCase
import com.sejin.todo_tdd_clean_architecture.domain.todo.GetTodoListUseCase
import com.sejin.todo_tdd_clean_architecture.domain.todo.UpdateTodoListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 필요한 UseCase
 * 1. [GetToDoListUseCase]
 * 2. [UpdateTodoUseCase]
 * 3. [DeleteAllTodoItemUseCase]
 */

internal class ListViewModel(
    private val getTodoListUseCase: GetTodoListUseCase,
    private val updateTodoListUseCase: UpdateTodoListUseCase,
    private val deleteAllTodoItemUseCase: DeleteAllTodoItemUseCase,
) : ViewModel() {
    private val _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
    val todoListLiveData: LiveData<ToDoListState> = _toDoListLiveData

    fun fetchData(): Job = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        _toDoListLiveData.postValue(ToDoListState.Success(getTodoListUseCase()))
    }

    fun updateEntity(todoEntity: ToDoEntity) = viewModelScope.launch {
        updateTodoListUseCase(todoEntity)
    }

    fun deleteAll() = viewModelScope.launch{
        _toDoListLiveData.postValue(ToDoListState.Loading)
        deleteAllTodoItemUseCase()
        _toDoListLiveData.postValue(ToDoListState.Success(getTodoListUseCase()))
    }
}