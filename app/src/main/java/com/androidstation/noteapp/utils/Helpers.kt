package com.androidstation.noteapp.utils

import android.content.Context
import android.widget.Toast

const val NOTE_DATA_BASE = "noteDataBase"

fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()