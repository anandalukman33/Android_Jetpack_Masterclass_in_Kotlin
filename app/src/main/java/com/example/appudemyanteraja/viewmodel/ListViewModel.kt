package com.example.appudemyanteraja.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.appudemyanteraja.database.DogDatabase
import com.example.appudemyanteraja.model.DogApiService
import com.example.appudemyanteraja.model.DogBreed
import com.example.appudemyanteraja.util.GsonUtils
import com.example.appudemyanteraja.util.Logger
import com.example.appudemyanteraja.util.NotificationUtil
import com.example.appudemyanteraja.util.SharedPreferencesHelper
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class ListViewModel(application: Application) : BaseViewModel(application) {

    private var prefHelper = SharedPreferencesHelper(getApplication())
    private val dogsService = DogApiService()
    private val disposable = CompositeDisposable()
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L  // Lima menit dalam nano second

    /**
     * Method Implementation
     */

    fun refresh() {
        checkCacheDuration()
        var updateTime = prefHelper.getUpdateTime()

        /**
         * Kondisi dimana ketika data yang diambil kurang dari lima menit dalam nano second
         * maka akan data tersebut akan diambil dari database
         * jika tidak maka akan diambil lewat endpoint atau API
         */
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            fetchFromDatabase()
        } else {
            fetchFromRemote()
        }
    }

    private fun checkCacheDuration() {
        val cachePreference = prefHelper.getCacheDuration()

        try {
            val cachePreferenceInt = cachePreference?.toInt() ?: 5 + 60
            refreshTime = cachePreferenceInt.times(1000 * 1000 * 1000L)
        } catch (e: NumberFormatException) { // pengecualian yang diinput selain integer, misal user input string akan masuk kesini
            e.printStackTrace()
            Toast.makeText(getApplication(), "Harus pake angka di pengaturan durasi!!", Toast.LENGTH_LONG).show()
        }
    }

    fun refreshBypassCache() {
        fetchFromRemote()
    }

    private fun fetchFromDatabase() {
        loading.value = true

        launch {
            val dogs = DogDatabase(getApplication()).dogDao().getAllDogs()
            dogsRetrieved(dogs)
            Logger.json(GsonUtils.bean2Json(dogs))
            //Toast.makeText(getApplication(), "Data Anjing diambil dari Database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(t: List<DogBreed>?) {
                        if (t != null) {
                            Logger.json(GsonUtils.bean2Json(t!!))
                            storeDogsLocally(t)
                            NotificationUtil(getApplication()).createNotification() // Implementasi Notification
                            //Toast.makeText(getApplication(), "Data Anjing diambil dari API", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(e: Throwable?) {
                        dogsLoadError.value = true
                        loading.value = false
                        e?.printStackTrace()
                        Logger.e("Error ListViewModel $e")
                    }

                })
        )
    }

    private fun dogsRetrieved(dogList: List<DogBreed>) {
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocally(list: List<DogBreed>) {
        launch {
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val result = dao.insertAll(*list.toTypedArray()) // Insert All Arrayof DogBreed

            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                ++i
            }
            dogsRetrieved(list)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}