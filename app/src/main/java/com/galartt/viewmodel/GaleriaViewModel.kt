package com.galartt.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.galartt.data.GaleriaDatabase
import com.galartt.model.Galeria
import com.galartt.repository.GaleriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GaleriaViewModel (application: Application): AndroidViewModel(application) {

    val getAllData: LiveData<List<Galeria>>

    private val repository:GaleriaRepository

    init {
        val galeriaDao = GaleriaDatabase.getDatabase(application).galeriaDao()
        repository = GaleriaRepository(galeriaDao)
        getAllData = repository.getAllData
    }

    fun addArte(galeria: Galeria){
        viewModelScope.launch(Dispatchers.IO){
            repository.addArte(galeria)
        }
    }

    fun updateArte(galeria: Galeria){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateArte(galeria)
        }
    }

    fun deleteArte(galeria: Galeria){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteArte(galeria)
        }
    }

}