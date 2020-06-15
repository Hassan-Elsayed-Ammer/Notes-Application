package com.androidstation.noteapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
//create Note Table (Class)
data class Note(
    //this Attribute is the column
    val title: String,
    val note: String

) : Serializable {
    //Serializable because         ew move from fragment to Another fragment
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}