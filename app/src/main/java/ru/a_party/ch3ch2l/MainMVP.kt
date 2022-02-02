package ru.a_party.ch3ch2l

import ru.a_party.ch3ch2l.model.data.AppState

interface MainMVP {

    interface Presenter<T : AppState, V : View> {
        fun attachView(view: V)
        fun detachView(view: V)
        fun getData(word: String, isOnline: Boolean)
    }

    interface View {
        fun renderData(appState: AppState)
    }


}