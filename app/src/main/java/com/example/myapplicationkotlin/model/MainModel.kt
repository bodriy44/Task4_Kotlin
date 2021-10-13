package com.example.myapplicationkotlin.model

import java.util.*

class MainModel {
    var notes: MutableList<Note>





    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getIndexNote(note: Note): Int{
        return notes.indexOf(note)
    }
    fun deleteNote(note: Note) {
        notes.removeAt(notes.indexOf(note))
    }

    fun getNote(index: Int): Note {
        return notes[index]
    }

    init {
        notes = ArrayList()
    }
}