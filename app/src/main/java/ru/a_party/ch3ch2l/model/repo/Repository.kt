package ru.a_party.ch3ch2l.model.repo

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}