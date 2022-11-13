package com.sejin.todo_tdd_clean_architecture.data.repository

import com.sejin.todo_tdd_clean_architecture.data.entity.ToDoEntity

class TestToDoRepository : ToDoRepository {

    private val toDoList: MutableList<ToDoEntity> = mutableListOf()
    override suspend fun getToDoList(): List<ToDoEntity> {
        return toDoList
    }

    override suspend fun insertToDoList(todoList: List<ToDoEntity>) {
        this.toDoList.addAll(todoList)
    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity): Boolean {
        val foundToDoEntity = toDoList.find { it.id == toDoItem.id }
        return if (foundToDoEntity == null) false
        else {
            this.toDoList[toDoList.indexOf(foundToDoEntity)] = toDoItem
            true
        }
    }

    override suspend fun deleteToDoItem(id: Long): Boolean{
        val foundToDoEntity = toDoList.find { it.id == id }
        if (foundToDoEntity == null) return false
        else run {
            this.toDoList.removeAt(toDoList.indexOf(foundToDoEntity))
            return true
        }

    }

    override suspend fun getToDoItem(itemId: Long): ToDoEntity? {
        return toDoList.find { it.id == itemId }
    }

    override suspend fun insertToDoItem(toDoEntity: ToDoEntity): Long {
        this.toDoList.add(toDoEntity)
        return toDoEntity.id
    }


    override suspend fun deleteAll() {
        toDoList.clear()
    }
}