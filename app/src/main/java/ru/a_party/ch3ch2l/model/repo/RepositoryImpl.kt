package ru.a_party.ch3ch2l.model.repo

import ru.a_party.ch3ch2l.model.data.DataModel
import ru.a_party.ch3ch2l.model.datasource.DataSource

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}