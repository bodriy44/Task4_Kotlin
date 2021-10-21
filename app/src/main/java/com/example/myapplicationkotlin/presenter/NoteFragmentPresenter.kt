package com.example.myapplicationkotlin.presenter

import com.example.myapplicationkotlin.model.MainModel
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.database.AppDatabase
import com.example.myapplicationkotlin.view.NoteView

class NoteFragmentPresenter(var db: AppDatabase, var view: NoteView) {
    private val model: MainModel

    fun setNotes(notes: MutableList<Note>){
        model.notes = notes
    }

    fun getNotes() = model.notes

    fun getIndexNote(note: Note) = model.getIndexNote(note)


    fun getSize() = model.getSize()

    suspend fun deleteNote(note: Note){
        db.noteDao().deleteNote(note)
        model.deleteNote(note)
    }

    init {
        model = MainModel()
    }
}