package ru.a_party.ch3ch2l.model.repo

import io.reactivex.Observable
import ru.a_party.ch3ch2l.model.data.DataModel
import ru.a_party.ch3ch2l.model.datasource.DataSource

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}