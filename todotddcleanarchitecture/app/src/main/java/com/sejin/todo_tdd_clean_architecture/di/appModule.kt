package com.sejin.todo_tdd_clean_architecture.di

import android.content.Context
import androidx.room.Room
import com.sejin.todo_tdd_clean_architecture.data.local.db.ToDoDatabase
import com.sejin.todo_tdd_clean_architecture.data.repository.DefaultToDoRepository
import com.sejin.todo_tdd_clean_architecture.data.repository.ToDoRepository
import com.sejin.todo_tdd_clean_architecture.domain.todo.*
import com.sejin.todo_tdd_clean_architecture.domain.todo.DeleteToDoItemUseCase
import com.sejin.todo_tdd_clean_architecture.domain.todo.InsertToDoUseCase
import com.sejin.todo_tdd_clean_architecture.domain.todo.UpdateToDoUseCase
import com.sejin.todo_tdd_clean_architecture.presentation.detail.DetailMode
import com.sejin.todo_tdd_clean_architecture.presentation.detail.DetailViewModel
import com.sejin.todo_tdd_clean_architecture.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.IO }
    single { Dispatchers.Main }
    //ViewModel
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) ->
        DetailViewModel(
            detailMode,
            id,
            get(),
            get(),
            get(),
            get()
        )
    }
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
    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }
}

internal fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()