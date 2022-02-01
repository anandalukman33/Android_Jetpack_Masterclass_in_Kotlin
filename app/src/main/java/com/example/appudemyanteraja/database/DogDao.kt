package com.example.appudemyanteraja.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appudemyanteraja.model.DogBreed

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg dogs: DogBreed): List<Long>

    @Query("SELECT * FROM dogs_table")
    suspend fun getAllDogs(): List<DogBreed>

    @Query("SELECT * FROM dogs_table WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int): DogBreed

    @Query("DELETE FROM dogs_table")
    suspend fun deleteAllDogs()
}