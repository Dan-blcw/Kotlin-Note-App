package com.dan.noteapp.dbase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dan.noteapp.models.dto.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)
    @Delete
    suspend fun delete(note: Note)
    @Query("SELECT * FROM Note_tb ORDER BY date DESC")
    fun getAllNotes(): LiveData<List<Note>>
    @Query("UPDATE Note_tb SET title = :title, note = :note , date = :date WHERE id = :id")
    suspend fun update(id: Int?, title: String?,note: String?,date: String?)
}