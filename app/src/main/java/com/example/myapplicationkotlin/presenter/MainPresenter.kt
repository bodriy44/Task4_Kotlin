package com.example.myapplicationkotlin.presenter

import com.example.myapplicationkotlin.model.MainModel
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.database.AppDatabase
import com.example.myapplicationkotlin.view.IMainView

class MainPresenter(private var view: IMainView,  private var db: AppDatabase) {
    val model: MainModel
    //var notes: List<Note>
        //get() = model.getNotes()

    fun saveNote(note: Note) {
        model.addNote(note)
        view.showRecycler()
    }

    fun createNote() {
        view.showCreateFragment()
    }

   /* fun deleteNote(note: Note) {
        model.deleteNote(note)
    }*/

    fun getIndexNote(note: Note): Int {
        return model.getIndexNote(note)
    }

    fun showNote(index: Int) {
        view.showNote(model.getNote(index))
    }


    private suspend fun addNote(note: Note) {
        db.noteDao().addNote(note)
    }

    suspend fun addNote(title: String, text: String) {
        addNote(Note(title, text))
    }

    suspend fun deleteNote(note: Note){
        db.noteDao().deleteNote(note)
        model.deleteNote(note)
    }

    suspend fun getAllNotes() = db.noteDao().getAll()


    init {
        model = MainModel()
    }
}