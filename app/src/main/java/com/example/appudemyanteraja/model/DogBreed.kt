package com.example.appudemyanteraja.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appudemyanteraja.constant.DogConstant.BRED_FOR
import com.example.appudemyanteraja.constant.DogConstant.BREEDS_FOR
import com.example.appudemyanteraja.constant.DogConstant.BREEDS_GROUP
import com.example.appudemyanteraja.constant.DogConstant.BREED_GROUP
import com.example.appudemyanteraja.constant.DogConstant.BREED_ID
import com.example.appudemyanteraja.constant.DogConstant.DOG_NAME
import com.example.appudemyanteraja.constant.DogConstant.DOG_URL
import com.example.appudemyanteraja.constant.DogConstant.ID
import com.example.appudemyanteraja.constant.DogConstant.LIFE_SPAN
import com.example.appudemyanteraja.constant.DogConstant.LIFE_SPANS
import com.example.appudemyanteraja.constant.DogConstant.NAME
import com.example.appudemyanteraja.constant.DogConstant.TEMPERAMENT
import com.example.appudemyanteraja.constant.DogConstant.URL
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dogs_table")
data class DogBreed (

    @PrimaryKey(autoGenerate = true)
    var uuid: Int,

    @ColumnInfo(name = BREED_ID)
    @SerializedName(ID)
    val breedId: String,

    @ColumnInfo(name = DOG_NAME)
    @SerializedName(NAME)
    val dogBreed: String,

    @ColumnInfo(name = LIFE_SPANS)
    @SerializedName(LIFE_SPAN)
    val lifeSpan: String,

    @ColumnInfo(name = BREEDS_GROUP)
    @SerializedName(BREED_GROUP)
    val breedGroup: String,

    @ColumnInfo(name = BREEDS_FOR)
    @SerializedName(BRED_FOR)
    val bredFor: String,

    @SerializedName(TEMPERAMENT)
    val temperament: String,

    @ColumnInfo(name = DOG_URL)
    @SerializedName(URL)
    val imageUrl: String
)