package com.androidstation.noteapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.repositories.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ew use the coroutine inside the view model
class NoteViewModel(
    app: Application,
    private val repo: NoteRepository

) : AndroidViewModel(app) {


    // add new and update exist.
    fun upsert(note: Note) = viewModelScope.launch {  repo.upsert(note)  }

    fun delete(note: Note) = viewModelScope.launch { repo.delete(note) }

    fun getAllNotesUseCoroutine() = viewModelScope.launch { repo.getAllNotes() }


}