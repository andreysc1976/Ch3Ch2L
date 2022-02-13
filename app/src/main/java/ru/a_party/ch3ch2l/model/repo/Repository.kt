package ru.a_party.ch3ch2l.model.repo


interface Repository<T> {
    suspend fun getData(word: String):T
}