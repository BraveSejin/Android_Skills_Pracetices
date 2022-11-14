package com.sejin.todo_tdd_clean_architecture.data.repository

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity
import com.sejin.todo_tdd_clean_architecture.data.local.db.dao.ToDoDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultToDoRepository(
    private val toDoDao: ToDoDao,
    private val ioDispatcher: CoroutineDispatcher
) : ToDoRepository {
    override suspend fun getToDoList(): List<ToDoEntity> = withContext(ioDispatcher) {
        toDoDao.getAll()
    }

    override suspend fun getToDoItem(itemId: Long): ToDoEntity? = withContext(ioDispatcher) {
        toDoDao.getById(itemId)
    }

    override suspend fun insertToDoList(todoList: List<ToDoEntity>) = withContext(ioDispatcher) {
        toDoDao.insert(todoList)
    }

    override suspend fun insertToDoItem(toDoEntity: ToDoEntity): Long = withContext(ioDispatcher) {
        toDoDao.insert(toDoEntity)
    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity) = withContext(ioDispatcher) {
        toDoDao.update(toDoItem)
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        toDoDao.deleteAll()
    }

    override suspend fun deleteToDoItem(id: Long) = withContext(ioDispatcher)
    {
        toDoDao.delete(id)
    }
}