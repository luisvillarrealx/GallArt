package com.galartt.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.galartt.model.Galeria

@Dao

interface GaleriaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addArte(galeria: Galeria)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateArte(galeria: Galeria)

    @Delete
    suspend fun deleteArte(galeria: Galeria)

    @Query("SELECT * FROM ARTE")
    fun getAllData(): LiveData<List<Galeria>>

}