package ru.a_party.ch3ch2l.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.a_party.ch3ch2l.model.viewmodel.MainViewModel
import ru.a_party.ch3ch2l.model.data.DataModel
import ru.a_party.ch3ch2l.model.datasource.DataSourceLocal
import ru.a_party.ch3ch2l.model.datasource.DataSourceRemote
import ru.a_party.ch3ch2l.model.repo.Repository
import ru.a_party.ch3ch2l.model.repo.RepositoryImpl


internal const val NAME_REMOTE = "Remote"
internal const val NAME_LOCAL = "Local"

val app = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)){RepositoryImpl(DataSourceRemote())}
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)){RepositoryImpl(DataSourceLocal())}
}

val mainScreen = module {
    viewModel{
        MainViewModel(get(named(NAME_REMOTE)))
    }
}