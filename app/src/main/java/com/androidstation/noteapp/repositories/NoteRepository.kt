package com.androidstation.noteapp.repositories

import androidx.lifecycle.LiveData
import com.androidstation.noteapp.db.Note
import com.androidstation.noteapp.db.NoteDao
import com.androidstation.noteapp.db.NoteDataBase

class NoteRepository(
    private val dataBase: NoteDataBase
) {

   // val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    // add Note
    suspend fun addNote(note: Note) = dataBase.getNoteDao().addNote(note)

    //update note
    suspend fun updateNote(note: Note) = dataBase.getNoteDao().updateNote(note)

    //delete note
    suspend fun delete(note: Note) = dataBase.getNoteDao().deleteNote(note)

    //get All Notes
    fun getAllNotes() = dataBase.getNoteDao().getAllNotes()




}