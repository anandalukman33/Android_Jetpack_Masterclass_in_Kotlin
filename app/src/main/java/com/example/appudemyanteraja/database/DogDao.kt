package com.example.appudemyanteraja.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appudemyanteraja.model.DogBreed

@Dao
interface DogDao {

    @Insert
    suspend fun insertAll(vararg dogs: DogBreed): List<Long>

    @Query("SELECT * FROM DogBreed")
    suspend fun getAllDogs(): List<DogBreed>

    @Query("SELECT * FROM DogBreed WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int): DogBreed

    @Query("DELETE FROM DogBreed")
    suspend fun deleteAllDogs()
}