package com.sejin.todo_tdd_clean_architecture.viewmodel.todo

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity
import com.sejin.todo_tdd_clean_architecture.domain.todo.GetTodoItemUseCase
import com.sejin.todo_tdd_clean_architecture.domain.todo.InsertTodoListUseCase
import com.sejin.todo_tdd_clean_architecture.presentation.list.ListViewModel
import com.sejin.todo_tdd_clean_architecture.presentation.list.ToDoListState
import com.sejin.todo_tdd_clean_architecture.viewmodel.ViewModelTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.inject

/**
 * [ListViewModel]을 테스트하기 위하 Unit Test Class
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test Item Update
 * 4. test Item Delete All
 */
internal class ListViewModelTest : ViewModelTest() {

    private val viewModel: ListViewModel by inject()
    private val insertTodoListUseCase: InsertTodoListUseCase by inject()
    private val getToDoItemUseCase: GetTodoItemUseCase by inject()
    private val mockList = (0..9).map {
        ToDoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

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
        insertTodoListUseCase(mockList)
    }

    // Test: 입력된 데이터를 불러와서 검증한다.
    @Test
    fun `test viewmodel fetch`(): Unit = runBlockingTest {
        val testObservable = viewModel.todoListLiveData.test()
        viewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(mockList)
            )
        )
    }

    // Test: 데이터를 업데이트 했을 떄 잘 반영되는가
    @Test
    fun `test iItem update`(): Unit = runBlockingTest {
        val todo = ToDoEntity(
            id = 1,
            title = "title 1",
            description = "description 1",
            hasCompleted = true
        )
        viewModel.updateEntity(todo)
        assert((getToDoItemUseCase(todo.id)?.hasCompleted ?: false) == todo.hasCompleted)
    }

    //Test: 데이터를 다 날렸을 때 빈 상태로 보여지는가
    @Test
    fun `test Item Delete All`(): Unit = runBlockingTest {
        val testObservable = viewModel.todoListLiveData.test()
        viewModel.deleteAll()
        testObservable.assertValueSequence( // 테스트용 Observable에서 깊은복사를 못해서 이상태로는 테스트 불가!
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf())
            )
        )
    }
}