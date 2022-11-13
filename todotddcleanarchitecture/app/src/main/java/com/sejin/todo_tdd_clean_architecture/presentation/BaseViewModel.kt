package com.sejin.todo_tdd_clean_architecture.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

internal abstract class BaseViewModel : ViewModel() {
    abstract fun fetchData(): Job

}