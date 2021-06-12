package com.vignesh.medilite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MedicineDao {
    @Insert
    suspend fun insert(medicine: Medicine)

    @Delete
    suspend fun delete(medicine: Medicine)

    @Query("Select * from medicine_table order by id ASC")
    fun getAllMedicines() : LiveData<List<Medicine>>

    @Query("Select * from medicine_table order by id ASC")
    fun getListMedicines() : List<Medicine>
}