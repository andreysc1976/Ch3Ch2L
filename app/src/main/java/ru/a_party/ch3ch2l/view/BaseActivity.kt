package ru.a_party.ch3ch2l.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.a_party.ch3ch2l.MainMVP
import ru.a_party.ch3ch2l.model.data.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity(), MainMVP.View {

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}