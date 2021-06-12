package com.vignesh.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao{
    @Insert
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note : Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes() : LiveData<List<Note>>
}