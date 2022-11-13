package com.sejin.todo_tdd_clean_architecture.viewmodel.todo

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity
import com.sejin.todo_tdd_clean_architecture.domain.todo.InsertTodoListUseCase
import com.sejin.todo_tdd_clean_architecture.presentation.list.ListViewModel
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
            listOf(mockList)
        )
    }

    // Test: 데이터를 업데이트 했을 떄 잘 반영되는가
//    @Test

}