package ru.a_party.ch3ch2l.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import ru.a_party.ch3ch2l.model.data.AppState
import ru.a_party.ch3ch2l.model.data.DataModel
import ru.a_party.ch3ch2l.model.datasource.DataSourceRemote
import ru.a_party.ch3ch2l.model.repo.Repository
import ru.a_party.ch3ch2l.model.repo.RepositoryImpl
import ru.a_party.ch3ch2l.model.utils.SchedulerProvider

class MainViewModel(
    private val repo: Repository<List<DataModel>> = RepositoryImpl(DataSourceRemote()),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {

    private val _appState = MutableLiveData<AppState>()
    val appState: LiveData<AppState>
        get() = _appState

    fun fetchData(word:String) {
        repo.getData(word)
            .map { AppState.Success(it) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                _appState.value = it
            })
    }
}


