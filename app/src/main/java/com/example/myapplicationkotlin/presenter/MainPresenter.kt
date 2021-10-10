package com.example.myapplicationkotlin.presenter

import com.example.myapplicationkotlin.model.MainModel
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.view.IMainView

class MainPresenter(private var view: IMainView) {
    private val model: MainModel
    val notes: List<Note>
        get() = model.getNotes()

    fun saveNote(note: Note) {
        model.addNote(note)
        view.showRecycler()
    }

    fun createNote() {
        view.showCreateFragment()
    }

    fun deleteNote(note: Note) {
        model.deleteNote(note)
    }

    fun getIndexNote(note: Note): Int {
        return model.getIndexNote(note)
    }

    fun showNote(index: Int) {
        view.showNote(model.getNote(index))
    }

    init {
        model = MainModel()
    }
}