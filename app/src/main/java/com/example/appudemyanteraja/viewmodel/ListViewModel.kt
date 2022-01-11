package com.example.appudemyanteraja.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appudemyanteraja.model.DogApiService
import com.example.appudemyanteraja.model.DogBreed
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ListViewModel : ViewModel() {
    private val dogsService = DogApiService()
    private val disposable = CompositeDisposable()
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    /**
     * Method Implementation
     */

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(t: List<DogBreed>?) {
                        dogs.value = t
                        dogsLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        dogsLoadError.value = true
                        loading.value = false
                        e?.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}