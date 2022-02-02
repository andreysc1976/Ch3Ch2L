package ru.a_party.ch3ch2l.view

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.a_party.ch3ch2l.MainMVP
import ru.a_party.ch3ch2l.model.data.AppState
import ru.a_party.ch3ch2l.model.data.DataModel
import ru.a_party.ch3ch2l.model.datasource.DataSourceRemote
import ru.a_party.ch3ch2l.model.repo.Repository
import ru.a_party.ch3ch2l.model.repo.RepositoryImpl
import ru.a_party.ch3ch2l.model.utils.SchedulerProvider

class PresenterImplementation<T : AppState, V : MainMVP.View>(
    val localRepository: Repository<List<DataModel>> = RepositoryImpl(DataSourceRemote()),
    val remoteRepository: Repository<List<DataModel>> = RepositoryImpl(DataSourceRemote()),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : MainMVP.Presenter<T, V> {


    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        (if (isOnline) localRepository else remoteRepository)
            .getData(word)
            .map{AppState.Success(it)}
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
            .subscribeWith(getObserver())
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }

}
