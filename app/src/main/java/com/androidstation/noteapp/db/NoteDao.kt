package com.androidstation.noteapp.db

import androidx.room.*

@Dao
interface NoteDao {

    //suspend key word before fun is coroutines scoop

    //Insert In table
    @Insert
    suspend fun addNote(note :Note)

    //Get All Note return list of notes
    //ORDER BY id DESC --> to display lasted note first
    @Query("SELECT * FROM note ORDER BY id DESC ")
    suspend fun getAllNotes() : List<Note>

    //add Multiple Notes
    @Insert
    suspend fun addMultipleNotes(vararg note: Note)

    //update note
    @Update
    suspend fun updateNote(note: Note )

    //delete
    @Delete
    suspend fun deleteNote(note: Note)



}