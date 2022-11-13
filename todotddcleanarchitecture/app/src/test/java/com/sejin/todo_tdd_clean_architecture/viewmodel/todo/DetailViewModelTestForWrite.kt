package com.sejin.todo_tdd_clean_architecture.viewmodel.todo

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity
import com.sejin.todo_tdd_clean_architecture.domain.todo.InsertToDoUseCase
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
 * [DetailViewModel]을 테스트하기 위한 Unit Test Class
 * 1. test viewModel fetch
 * 2. test insert todo
 */
@ExperimentalCoroutinesApi
internal class DetailViewModelTestForWrite : ViewModelTest() {

    private val id = 0L

    private val detailViewModel: DetailViewModel by inject { parametersOf(DetailMode.WRITE, 1) }
    private val listViewModel: ListViewModel by inject()
    private val insertToDoUseCase: InsertToDoUseCase by inject()

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

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.todoDetailLiveData.test()

        detailViewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Write
            )
        )
    }

    @Test
    fun `test insert todo`() = runBlockingTest {
        val detailTObservable = detailViewModel.todoDetailLiveData.test()
        val listTestObservable = listViewModel.todoListLiveData.test()

        detailViewModel.writeToDo(
            title = todo.title,
            description = todo.description
        )

        detailTObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )

        assert(detailViewModel.detailMode == DetailMode.DETAIL)
        assert(detailViewModel.id == id)

        //뒤로 나가서 리스트 보기
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf(
                    todo
                ))
            )
        )

    }
}