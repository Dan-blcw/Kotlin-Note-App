package com.dan.noteapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dan.noteapp.dbase.NoteDB
import com.dan.noteapp.dbase.NotesRepository
import com.dan.noteapp.models.dto.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val repo: NotesRepository
    val allNotes: LiveData<List<Note>>
    init {
        val dao = NoteDB.getDB(application).getNoteDao()
        repo = NotesRepository(dao)
        allNotes = repo.allNotes
    }
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repo.delete(note)
    }
    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repo.update(note)
    }
}