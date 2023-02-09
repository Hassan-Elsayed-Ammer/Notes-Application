package com.androidstation.noteapp.ui

import android.app.Application
import androidx.lifecycle.*
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.db.NoteDataBase
import com.androidstation.noteapp.repositories.NoteRepository
import kotlinx.coroutines.launch

// we use the coroutine inside the view model
class NoteViewModel(
    application: Application,
    private val noteRepo: NoteRepository

) : AndroidViewModel(application) {

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepo.addNote(note)
        }
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepo.updateNote(note)
    }

    fun delete(note: Note) = viewModelScope.launch { noteRepo.delete(note) }

    // we don't need coroutine here because we read from main thread
    fun getAllNotes(): LiveData<List<Note>> {
        return noteRepo.getAllNotes()
    }


}