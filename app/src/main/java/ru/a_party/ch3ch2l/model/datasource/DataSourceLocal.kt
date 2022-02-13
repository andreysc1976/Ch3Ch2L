package ru.a_party.ch3ch2l.model.datasource

import ru.a_party.ch3ch2l.model.data.DataModel

class DataSourceLocal:DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }
}