package com.example.myapplicationkotlin.view

import com.example.myapplicationkotlin.model.Note

interface MainView {
    fun showCreateFragment()
    fun showRecycler()
    fun showNote(note: Note)
}