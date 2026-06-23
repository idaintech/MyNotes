package com.ida.mynotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes") //db dagi tablica
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val description: String,
    val date: Long = System.currentTimeMillis(),
    var name: String = ""
    //7446567473747 -> 12 June 2026 14:36
)
//Migration
