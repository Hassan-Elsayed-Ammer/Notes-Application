package com.androidstation.noteapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidstation.noteapp.repositories.NoteRepository

class NoteViewModelFactory(
    val app:Application ,
    private val noteRepository: NoteRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app,noteRepository) as T
    }

}