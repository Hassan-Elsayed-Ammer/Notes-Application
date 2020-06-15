package com.androidstation.noteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock


@Database(
    //define All tables (entities)
    entities = [Note::class],
    version = 1
)
abstract class NoteDataBase  :RoomDatabase(){

    abstract fun getNoteDao() :NoteDao

    companion object{

        @Volatile private var instance :NoteDataBase? = null
        private val Lock = Any()

        operator fun invoke(context : Context)= instance?: synchronized(Lock){

            instance?: buildDataBase (context).also {
                instance = it
            }
        }


        //build Data base
        private fun buildDataBase(context: Context) =Room.databaseBuilder(
            context.applicationContext,
            NoteDataBase::class.java,
            "notedatabase"
        ).build()
    }
}