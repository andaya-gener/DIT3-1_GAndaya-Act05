package com.example.notekeeperapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeperapp.data.DatabaseHelper

class AddEditNoteActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        dbHelper = DatabaseHelper(this)
        val titleInput = findViewById<EditText>(R.id.etTitle)
        val contentInput = findViewById<EditText>(R.id.etContent)
        val btnSave = findViewById<Button>(R.id.btnSave)

        noteId = intent.getIntExtra("id", -1)
        if (noteId != -1) {
            titleInput.setText(intent.getStringExtra("title"))
            contentInput.setText(intent.getStringExtra("content"))
        }

        btnSave.setOnClickListener {
            val title = titleInput.text.toString()
            val content = contentInput.text.toString()

            if (noteId == -1) {
                dbHelper.insertNote(title, content)
                Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show()
            } else {
                dbHelper.updateNote(noteId, title, content)
                Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show()
            }

            finish()
        }
    }
}