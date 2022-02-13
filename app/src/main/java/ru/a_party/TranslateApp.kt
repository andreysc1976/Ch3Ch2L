package ru.a_party

import android.app.Application
import org.koin.core.context.startKoin
import ru.a_party.ch3ch2l.di.app
import ru.a_party.ch3ch2l.di.mainScreen

class TranslateApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(listOf(app,mainScreen))
        }
    }
}