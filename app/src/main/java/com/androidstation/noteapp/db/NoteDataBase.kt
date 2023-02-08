package com.androidstation.noteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidstation.noteapp.utils.NOTE_DATA_BASE


@Database(
    //define All tables (entities)
    entities = [Note::class],
    version = 1
)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDataBase? = null

        fun getDataBase(context: Context): NoteDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    NOTE_DATA_BASE
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}