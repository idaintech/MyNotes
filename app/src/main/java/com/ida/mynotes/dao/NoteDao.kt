package com.ida.mynotes.dao

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ida.mynotes.data.Note

//DAO - Data Access Object
@Dao
interface NoteDao {
    //requests
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

   /* @Query("SELECT title, description FROM notes ORDER BY title DESC")
    fun getAllNotes1(): List<Note>*/

    @Insert
    fun add(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    /*CRUD -> Create (Insert)
    R -> Read (Query)
    U -> Update (Update)
    D -> Delete (Delete)*/
}