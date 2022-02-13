package ru.a_party.ch3ch2l.model.datasource


import ru.a_party.ch3ch2l.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImpl = RetrofitImpl()) :
    DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}