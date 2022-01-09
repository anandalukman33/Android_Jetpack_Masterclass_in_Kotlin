package com.example.appudemyanteraja.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appudemyanteraja.model.DogBreed

class DetailViewModel: ViewModel() {
    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog = DogBreed(
            "1",
            "Corgi",
            "15 Years",
            "breedGroup",
            "breedFor",
            "temperament"
        )
        dogLiveData.value = dog
    }
}