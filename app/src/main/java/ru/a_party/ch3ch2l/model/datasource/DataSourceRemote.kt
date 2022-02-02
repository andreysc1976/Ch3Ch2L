package ru.a_party.ch3ch2l.model.datasource

import io.reactivex.Observable
import ru.a_party.ch3ch2l.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImpl = RetrofitImpl()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}