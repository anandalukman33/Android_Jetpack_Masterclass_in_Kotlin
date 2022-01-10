package com.example.appudemyanteraja.model

import com.example.appudemyanteraja.constant.UrlConstant.BASE_URL
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DogApiService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(DogsApi::class.java)

    fun getDogs(): Single<List<DogBreed>> {
        return api.getDogs()
    }
}