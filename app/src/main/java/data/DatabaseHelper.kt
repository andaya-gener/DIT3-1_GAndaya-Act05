package com.example.notekeeperapp.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "notes.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE notes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "content TEXT," +
                    "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

    fun insertNote(title: String, content: String): Long {
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
        }
        return writableDatabase.insert("notes", null, values)
    }

    fun updateNote(id: Int, title: String, content: String): Int {
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
        }
        return writableDatabase.update("notes", values, "id=?", arrayOf(id.toString()))
    }

    fun deleteNote(id: Int): Int {
        return writableDatabase.delete("notes", "id=?", arrayOf(id.toString()))
    }

    fun getAllNotes(): Cursor {
        return readableDatabase.rawQuery("SELECT * FROM notes ORDER BY id DESC", null)
    }
}