package com.androidstation.noteapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//create Note Table (Class)
@Entity(tableName = "note_item")
data class Note(
    //this Attribute is the column
    @ColumnInfo(name = "item_title")
    var title: String,
    @ColumnInfo(name = "item_note")
    var note: String

) : Serializable {
    //Serializable because we will move from fragment to Another fragment
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}