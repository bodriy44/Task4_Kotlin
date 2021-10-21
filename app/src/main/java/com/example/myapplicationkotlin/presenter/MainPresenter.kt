package com.example.myapplicationkotlin.presenter

import com.example.myapplicationkotlin.model.MainModel
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.database.AppDatabase
import com.example.myapplicationkotlin.view.IMainView

class MainPresenter(private var view: IMainView,  private var db: AppDatabase) {
    val model: MainModel

    fun saveNote(note: Note) {
        model.addNote(note)
        view.showRecycler()
    }

    fun getNotes() = model.notes

    fun setNotes(notes: MutableList<Note>){
        model.notes = notes
    }

    private suspend fun addNote(note: Note) {
        db.noteDao().addNote(note)
    }

    suspend fun addNote(title: String, text: String) {
        addNote(Note(title, text))
    }

    suspend fun getAllNotes() = db.noteDao().getAll()

    init {
        model = MainModel()
    }
}