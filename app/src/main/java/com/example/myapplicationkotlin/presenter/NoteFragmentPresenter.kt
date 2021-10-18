package com.example.myapplicationkotlin.presenter

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationkotlin.model.MainModel
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.database.AppDatabase
import com.example.myapplicationkotlin.view.NoteView

class NoteFragmentPresenter(var db: AppDatabase, var view: NoteView) {
    private val model: MainModel

    fun setNotes(notes: MutableList<Note>){
        Log.i("notesP", notes.toString())
        model.notes = notes
        Log.i("notesModel", model.notes.toString())
    }

    fun getNotes(): MutableList<Note>{
        Log.i("notesModelGet", model.notes.toString())
        return model.notes
    }

    fun getIndexNote(note: Note): Int {
        return model.getIndexNote(note)
    }

    fun getSize(): Int{
        return model.getSize()
    }

    suspend fun deleteNote(note: Note){
        db.noteDao().deleteNote(note)
        model.deleteNote(note)
    }

    init {
        model = MainModel()
    }
}