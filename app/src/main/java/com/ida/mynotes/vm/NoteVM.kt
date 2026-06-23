package com.ida.mynotes.vm

import androidx.lifecycle.ViewModel
import com.ida.mynotes.dao.NoteDao
import com.ida.mynotes.data.Note


//Defendency Injection -> DI
class NoteVM(val dao: NoteDao): ViewModel(){
    fun add(note: Note){
        dao.add(note)
    }
    fun getAllNotes(): List<Note>{
        return dao.getAllNotes()
    }
}
