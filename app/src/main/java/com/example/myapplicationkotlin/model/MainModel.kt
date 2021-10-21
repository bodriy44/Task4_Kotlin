package com.example.myapplicationkotlin.model

import java.util.*

class MainModel {
    var notes: MutableList<Note>

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getIndexNote(note: Note) = notes.indexOf(note)

    fun deleteNote(note: Note) {
        notes.removeAt(notes.indexOf(note))
    }

    fun getSize() = notes.size

    fun getNote(index: Int) = notes[index]

    init {
        notes = ArrayList()
    }
}