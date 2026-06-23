package com.ida.mynotes.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ida.mynotes.data.NoteDb
import com.ida.mynotes.vm.NoteVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
   single {
        //db
        Room.databaseBuilder(
            get(),
            NoteDb::class.java,
            "notes_db"
        )
            .allowMainThreadQueries()
            .build()
    }
    single {
        get<NoteDb>().getDao()
    }
}

val viewModelModule = module {
    viewModel {
        NoteVM(get())
    }
}

