package com.vignesh.medilite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicineViewModal(application: Application) : AndroidViewModel(application) {

    val allMedicines : LiveData<List<Medicine>>
    private val repository : MedicineRepository

    init {
        val dao = MedicineDatabase.getDatabase(application).getMedicineDao()
        repository = MedicineRepository(dao)
        allMedicines = repository.allMedicines
    }

    fun deleteMedicine(medicine: Medicine) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(medicine)
    }

    fun insertMedicine(medicine: Medicine) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(medicine)
    }
}