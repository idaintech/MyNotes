package com.ida.mynotes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.ida.mynotes.data.Note
import com.ida.mynotes.data.NoteDb
import com.ida.mynotes.extention.convertToDate
import com.ida.mynotes.ui.theme.MyNotesTheme
import com.ida.mynotes.vm.NoteVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    //private val vm: NoteVM by viewModel()
    lateinit var db: NoteDb
    private lateinit var vm: NoteVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        db = Room.databaseBuilder(
            applicationContext,
            NoteDb::class.java,
            "notes_db"
        ).allowMainThreadQueries().build()

        vm = NoteVM(db.getDao())

        /*Koin - jardemshi
        DI - official Dagger Hilt Google*/

        setContent {
            MyNotesTheme {
                UI()
            }
        }
    }

    @Composable
    fun UI() {
        var showDialog by remember {
            mutableStateOf(false)
        }
        var notes by remember {
            mutableStateOf(listOf<Note>())
        }

        notes = vm.getAllNotes()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Text("add")
                }
            }) { ip ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(ip)
            ) {
                items(notes) { note ->
                   ItemUI(note)
                }

            }
        }
        //Migration
//        Navigation Drawer
        if(showDialog){
            AddDialog { showDialog = false }
        }
    }


    @Composable
    fun ItemUI(note: Note){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(note.title!!, fontSize = 18.sp)
            Text(note.date.convertToDate(), fontSize = 16.sp)
        }
    }

    @Composable
    fun AddDialog(onCloseDialog:() -> Unit){
        var title by remember {
            mutableStateOf("")
        }
        var description by remember {
            mutableStateOf("")
        }
        AlertDialog(
            onDismissRequest = {
                //ekran qalegen jerin basqanda
            // yamasa programmadan shigip ketkende
            // nezat islew kk ekenin jazamiz
                onCloseDialog()
            },
            dismissButton = {
                //razi emespen
                Button(onClick = {
                    onCloseDialog()
                }) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                //raziliq bildiremen
                Button(onClick = {
                    vm.add(Note(
                        title = title,
                        description = description
                    ))
                    onCloseDialog()
                }) {
                    Text("Add")
                }
            },
            title = {
                //dialog ui in sogamiz
                Column() {
                    Text("Add user", fontSize = 18.sp)
                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            title = it
                        },
                        label = {
                            Text("Add title for you note")
                        },
                        maxLines = 1
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = {
                            description = it
                        },
                        label = {
                            Text("Add title for you note")
                        },
                        maxLines = 10
                    )
                }
            }
        )
    }

}
