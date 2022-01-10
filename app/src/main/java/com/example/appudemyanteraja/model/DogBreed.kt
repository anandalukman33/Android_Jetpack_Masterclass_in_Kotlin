package com.example.appudemyanteraja.model

import com.example.appudemyanteraja.constant.UrlConstant.BRED_FOR
import com.example.appudemyanteraja.constant.UrlConstant.BREED_GROUP
import com.example.appudemyanteraja.constant.UrlConstant.ID
import com.example.appudemyanteraja.constant.UrlConstant.LIFE_SPAN
import com.example.appudemyanteraja.constant.UrlConstant.NAME
import com.example.appudemyanteraja.constant.UrlConstant.TEMPERAMENT
import com.example.appudemyanteraja.constant.UrlConstant.URL
import com.google.gson.annotations.SerializedName

data class DogBreed (

    @SerializedName(ID)
    val breedId: String? = null,

    @SerializedName(NAME)
    val dogBreed: String? = null,

    @SerializedName(LIFE_SPAN)
    val lifeSpan: String? = null,

    @SerializedName(BREED_GROUP)
    val breedGroup: String? = null,

    @SerializedName(BRED_FOR)
    val bredFor: String? = null,

    @SerializedName(TEMPERAMENT)
    val temperament: String? = null,

    @SerializedName(URL)
    val imageUrl: String? = null

)