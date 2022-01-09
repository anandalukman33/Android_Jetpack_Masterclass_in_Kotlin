package com.example.appudemyanteraja.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appudemyanteraja.model.DogBreed

class ListViewModel: ViewModel() {
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    /**
     * Method Implementation
     */

    fun refresh() {
        val firstDog = DogBreed(
            "1",
            "Corgi",
            "15 Years",
            "breedGroup",
            "breedFor",
            "temperament"
        )
        val secondDog = DogBreed(
            "2",
            "Labrador",
            "10 Years",
            "breedGroup",
            "breedFor",
            "temperament"
        )
        val thirdDog = DogBreed(
            "3",
            "Rotwailer",
            "20 Years",
            "breedGroup",
            "breedFor",
            "temperament"
        )

        val dogList = arrayListOf<DogBreed>(firstDog, secondDog, thirdDog)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}