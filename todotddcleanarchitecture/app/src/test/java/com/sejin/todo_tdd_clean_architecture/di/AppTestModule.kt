package com.sejin.todo_tdd_clean_architecture.di

import com.sejin.todo_tdd_clean_architecture.data.repository.TestToDoRepository
import com.sejin.todo_tdd_clean_architecture.data.repository.ToDoRepository
import com.sejin.todo_tdd_clean_architecture.domain.todo.GetTodoListUseCase
import com.sejin.todo_tdd_clean_architecture.domain.todo.InsertTodoListUseCase
import com.sejin.todo_tdd_clean_architecture.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    //ViewModel
    viewModel { ListViewModel(get()) }
    //UseCase
    factory { GetTodoListUseCase(get()) }
    factory { InsertTodoListUseCase(get()) }
    // Repository
    single<ToDoRepository> { TestToDoRepository() }
}