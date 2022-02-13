package ru.a_party.ch3ch2l.model.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.a_party.ch3ch2l.model.data.AppState

abstract class BaseViewModel<T: AppState>
    (
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel(){

    protected val coroutinesScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler{_,throwable ->
            handleError(throwable)
        })

    override fun onCleared(){
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob(){
        coroutinesScope.coroutineContext.cancelChildren()
    }

    abstract fun getData(word:String)
    abstract fun handleError(error:Throwable)
}