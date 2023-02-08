package com.androidstation.noteapp.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

//create Note Table (Class)
@Entity(tableName = "note_item")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 ,
    //this Attribute is the column
    @ColumnInfo(name = "item_title")
    var title: String,
    @ColumnInfo(name = "item_note")
    var body: String

) : Parcelable {
    //Parcelable because we will move from fragment to Another fragment

}