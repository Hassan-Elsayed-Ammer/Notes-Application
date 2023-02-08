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

    private val notesList = mutableListOf<Note>()


    private val _noteMutableList: MutableLiveData<List<Note>> = MutableLiveData()
    val noteLiveData: LiveData<List<Note>> = _noteMutableList

    /**
     * use viewModelScope is a efficient way to tie view Activity/Fragment Life with Coroutine Life */
    // add new and update exist.
//    init {
//        // make live data list = normal data list
//        _noteMutableList.value = notesList
//        val dao = NoteDataBase.getDataBase(application).getNoteDao()
//        repo = NoteRepository(dao)
//
//    }

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