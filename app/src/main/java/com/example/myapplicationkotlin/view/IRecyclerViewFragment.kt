package com.example.myapplicationkotlin.view

import com.example.myapplicationkotlin.model.Note

interface IRecyclerViewFragment {
    fun createNote()
    val notes: List<Note>
}