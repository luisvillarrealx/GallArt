package com.galartt.repository

import androidx.lifecycle.LiveData
import com.galartt.data.GaleriaDao
import com.galartt.model.Galeria

class GaleriaRepository (private val galeriaDao: GaleriaDao) {

    val getAllData: LiveData<List<Galeria>> = galeriaDao.getAllData()

    suspend fun addArte(galeria: Galeria){
        galeriaDao.addArte(galeria)
    }

    suspend fun updateArte(galeria: Galeria){
        galeriaDao.updateArte(galeria)
    }

    suspend fun deleteArte(galeria: Galeria){
        galeriaDao.deleteArte(galeria)
    }
}