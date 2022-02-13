package ru.a_party.ch3ch2l.model.datasource

import ru.a_party.ch3ch2l.model.data.DataModel

interface DataSource<T> {
    suspend fun getData(word: String): List<DataModel>
}