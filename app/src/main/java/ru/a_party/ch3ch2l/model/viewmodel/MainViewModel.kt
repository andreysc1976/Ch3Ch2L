package ru.a_party.ch3ch2l.model.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.a_party.ch3ch2l.model.data.AppState
import ru.a_party.ch3ch2l.model.data.DataModel
import ru.a_party.ch3ch2l.model.datasource.DataSourceRemote
import ru.a_party.ch3ch2l.model.repo.Repository
import ru.a_party.ch3ch2l.model.repo.RepositoryImpl


class MainViewModel(
    //private val repositoryRemote: Repository<List<DataModel>>
    private val repositoryRemote: Repository<List<DataModel>>
) : BaseViewModel<AppState>() {

    private val _appStateForObserve = MutableLiveData<AppState>()

    fun subscribe(): LiveData<AppState> {
        return _appStateForObserve
    }


    override fun getData(word: String) {
        _appStateForObserve.value = AppState.Loading
        cancelJob()
        coroutinesScope.launch { startGetData(word) }
    }

    private suspend fun startGetData(word: String) = withContext(Dispatchers.IO){
        _appStateForObserve.postValue(AppState.Success(repositoryRemote.getData(word)))
    }

    override fun handleError(error: Throwable) {
        error.message?.let { Log.e("ERROR", it) }
    }
}


