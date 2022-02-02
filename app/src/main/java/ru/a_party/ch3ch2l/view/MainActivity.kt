package ru.a_party.ch3ch2l.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import ru.a_party.ch3ch2l.MainMVP
import ru.a_party.ch3ch2l.databinding.ActivityMainBinding
import ru.a_party.ch3ch2l.model.data.AppState

class MainActivity : BaseActivity<AppState>(),SearchFragment.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        TranslateAdapter()
    }

    private var searchWord:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            val searchFragment = SearchFragment.newInstance()
            searchFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
        binding.rvTranslate.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvTranslate.adapter = adapter
    }

    override fun createPresenter(): MainMVP.Presenter<AppState, MainMVP.View> {
        return PresenterImplementation()
    }

    override fun renderData(appState: AppState) {
        when (appState){
            is AppState.Loading -> {
                binding.progressBar.visibility =VISIBLE
                binding.errorScreen.visibility = GONE
                binding.successScreen.visibility = GONE
            }
            is AppState.Error ->{
                binding.progressBar.visibility =GONE
                binding.errorScreen.visibility = VISIBLE
                binding.successScreen.visibility = GONE

                binding.errorText.text = appState.error.message
                binding.buttonReload.setOnClickListener {
                    searchWord?.let {
                            word -> presenter.getData(word,true)
                    }
                }
            }
            is AppState.Success ->{
                binding.progressBar.visibility =GONE
                binding.errorScreen.visibility = GONE
                binding.successScreen.visibility = VISIBLE

                adapter.data=appState.data
            }
        }

    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    override fun onClick(word: String) {
        searchWord = word
        presenter.getData(word,true)
    }
}