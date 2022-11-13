package com.sejin.todo_tdd_clean_architecture.di

import com.sejin.todo_tdd_clean_architecture.data.repository.TestToDoRepository
import com.sejin.todo_tdd_clean_architecture.data.repository.ToDoRepository
import com.sejin.todo_tdd_clean_architecture.domain.todo.*
import com.sejin.todo_tdd_clean_architecture.presentation.detail.DetailMode
import com.sejin.todo_tdd_clean_architecture.presentation.detail.DetailViewModel
import com.sejin.todo_tdd_clean_architecture.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    //ViewModel
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode, id, get(), get(), get()) }
    //UseCase
    factory { GetTodoListUseCase(get()) }
    factory { InsertToDoUseCase(get()) }
    factory { InsertTodoListUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }
    factory { UpdateTodoListUseCase(get()) }
    factory { GetTodoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllTodoItemUseCase(get()) }

    // Repository
    single<ToDoRepository> { TestToDoRepository() }
}