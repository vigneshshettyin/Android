package com.vignesh.medilite

import androidx.lifecycle.LiveData

class MedicineRepository( val medicineDao: MedicineDao) {

    val allMedicines : LiveData<List<Medicine>> = medicineDao.getAllMedicines()

    suspend fun insert(medicine: Medicine){
        medicineDao.insert(medicine)
    }

    suspend fun delete(medicine: Medicine){
        medicineDao.delete(medicine)
    }
}