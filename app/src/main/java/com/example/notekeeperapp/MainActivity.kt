package com.example.notekeeperapp

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeperapp.adapter.NoteAdapter
import com.example.notekeeperapp.data.DatabaseHelper
import com.example.notekeeperapp.data.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: NoteAdapter
    private lateinit var noteList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        noteList = loadNotes()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(noteList,
            onItemClick = { note ->
                val intent = Intent(this, AddEditNoteActivity::class.java)
                intent.putExtra("id", note.id)
                intent.putExtra("title", note.title)
                intent.putExtra("content", note.content)
                startActivity(intent)
            },
            onDeleteClick = { note ->
                dbHelper.deleteNote(note.id)
                noteList.remove(note)
                adapter.notifyDataSetChanged()
            })
        recyclerView.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)
        fab.setOnClickListener {
            startActivity(Intent(this, AddEditNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        noteList.clear()
        noteList.addAll(loadNotes())
        adapter.notifyDataSetChanged()
    }

    private fun loadNotes(): MutableList<Note> {
        val list = mutableListOf<Note>()
        val cursor: Cursor = dbHelper.getAllNotes()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val content = cursor.getString(2)
            val timestamp = cursor.getString(3)
            list.add(Note(id, title, content, timestamp))
        }
        cursor.close()
        return list
    }
}