package com.example.myapplicationkotlin.view

import com.example.myapplicationkotlin.model.Note

interface IMainView {
    fun showCreateFragment()
    fun showRecycler()
    fun showNote(note: Note)
}