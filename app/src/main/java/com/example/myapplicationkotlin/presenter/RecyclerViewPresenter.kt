package com.example.myapplicationkotlin.presenter

import com.example.myapplicationkotlin.model.MainModel
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.database.AppDatabase
import com.example.myapplicationkotlin.view.IMainView

class RecyclerViewPresenter (var db: AppDatabase, var view: IMainView) {
    private val model: MainModel

    fun setNotes(notes: MutableList<Note>){
        model.notes = notes
    }

    fun createNote() {
        view.showCreateFragment()
    }

    fun showNote(index: Int) {
        view.showNote(model.getNote(index))
    }

    fun getNotes() = model.notes


    suspend fun getAllNotes() = db.noteDao().getAll()

    init {
        model = MainModel()
    }
}