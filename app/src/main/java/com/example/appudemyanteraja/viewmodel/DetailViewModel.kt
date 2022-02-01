package com.example.appudemyanteraja.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.appudemyanteraja.database.DogDatabase
import com.example.appudemyanteraja.model.DogBreed
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid) // mendapatkan data berdasarkan id dari database
            dogLiveData.value = dog
        }
    }
}