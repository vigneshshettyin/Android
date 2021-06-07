package com.vignesh.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModal: NoteViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recycleView.adapter = adapter

        viewModal = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModal::class.java)

        viewModal.allNotes.observe(this, Observer {list->list?.let {
            adapter.updateList(it)
        }
        })

    }

    override fun onItemClicked(note: Note) {
        viewModal.deleteNote(note)
    }

    fun submitData(view: View) {
        val input = findViewById<EditText>(R.id.input) as EditText
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModal.insertNote(Note(noteText))
        }
    }
}