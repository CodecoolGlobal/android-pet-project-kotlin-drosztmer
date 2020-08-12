package com.codecool.petproject.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecool.petproject.model.Character
import com.codecool.petproject.model.CharactersApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(private var charactersApi: CharactersApi) : ViewModel() {

    private val disposable = CompositeDisposable()

    val characters = MutableLiveData<List<Character>>()
    val characterLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        refresh()
    }

    fun refresh() {
        getCharacters()
    }

    private fun getCharacters() {
        loading.value = true
        disposable.add(
            charactersApi.getCharacters()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Character>>() {
                    override fun onSuccess(value: List<Character>) {
                        characters.value = value
                        characterLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        println(e.message)
                        characterLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}