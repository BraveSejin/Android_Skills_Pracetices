package com.sejin.todo_tdd_clean_architecture.viewmodel.todo

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity
import com.sejin.todo_tdd_clean_architecture.domain.todo.InsertTodoItemUseCase
import com.sejin.todo_tdd_clean_architecture.presentation.detail.DetailMode
import com.sejin.todo_tdd_clean_architecture.presentation.detail.DetailViewModel
import com.sejin.todo_tdd_clean_architecture.presentation.detail.ToDoDetailState
import com.sejin.todo_tdd_clean_architecture.presentation.list.ListViewModel
import com.sejin.todo_tdd_clean_architecture.presentation.list.ToDoListState
import com.sejin.todo_tdd_clean_architecture.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

/**
 * [DEtailViewModel]을 테스트하기 위한 Unit Test Class
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test delete todo
 * 4. test Update todo
 */
@ExperimentalCoroutinesApi
internal class DetailViewModelTest : ViewModelTest() {

    private val id = 1L

    private val detailViewModel: DetailViewModel by inject { parametersOf(DetailMode.DETAIL, 1) }
    private val listViewModel: ListViewModel by inject()

    private val insertTodoItemUseCase: InsertTodoItemUseCase by inject()

    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    /**
     * 필요한 Usecase들
     * 1. InsertToDOListUseCase
     * 2. GetToDoItemUseCase
     */

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertTodoItemUseCase(todo)
    }

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.todoDetailLiveData.test()

        detailViewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )
    }

    @Test
    fun `test delete todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.todoDetailLiveData.test()
        detailViewModel.deleteTodo()

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Delete
            )
        )

        val listTestObservable = listViewModel.todoListLiveData.test()
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf())
            )
        )

    }

    @Test
    fun `test update todo`() = runBlockingTest {

        val testObservable = detailViewModel.todoDetailLiveData.test()
        val updateTitle = "title 1 update"
        val updateDescription = "descriontion 1 update"
        val updateToDo = todo.copy(
            title = updateTitle, description = updateDescription
        )

        detailViewModel.writeToDo(
            title = updateTitle,
            description = updateDescription
        )

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(updateToDo)
            )
        )
    }
}