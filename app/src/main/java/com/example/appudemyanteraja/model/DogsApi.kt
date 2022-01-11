package com.example.appudemyanteraja.model

import com.example.appudemyanteraja.constant.UrlConstant.END_POINT
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DogsApi {
    @GET(END_POINT)
    fun getDogs(): Single<List<DogBreed>>
}