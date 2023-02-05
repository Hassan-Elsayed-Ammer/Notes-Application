package com.androidstation.noteapp.repositories

import androidx.lifecycle.LiveData
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.db.NoteDataBase

class NoteRepository(
    private val dataBase: NoteDataBase
) {

    // add new and update exist.
    suspend fun upsert(note: Note) = dataBase.getNoteDao().addNote(note)

    suspend fun delete(note: Note) = dataBase.getNoteDao().deleteNote(note)

    suspend fun getAllNotes(): LiveData<List<Note>> = dataBase.getNoteDao().getAllNotes()


}