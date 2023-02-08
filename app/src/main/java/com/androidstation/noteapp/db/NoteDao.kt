package com.androidstation.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    //suspend key word before fun is coroutines scoop

    //Insert In table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    //update note
    @Update
    suspend fun updateNote(note: Note)

    //delete
    @Delete
    suspend fun deleteNote(note: Note)

    //Get All Note return list of notes
    //ORDER BY id DESC --> to display lasted note first
    @Query("SELECT * FROM note_item ORDER BY id DESC ")
    fun getAllNotes(): LiveData<List<Note>>

    //add Multiple Notes
    @Insert
    suspend fun addMultipleNotes(vararg note: Note)






}