package ru.a_party.ch3ch2l.model.datasource


import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.a_party.ch3ch2l.model.data.DataModel


interface RetrofitApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}
