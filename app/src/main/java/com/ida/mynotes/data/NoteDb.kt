package com.ida.mynotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ida.mynotes.dao.NoteDao

@Database(
    entities = [Note::class],
    version = 2
)
abstract class NoteDb: RoomDatabase() {
    abstract fun getDao(): NoteDao
}