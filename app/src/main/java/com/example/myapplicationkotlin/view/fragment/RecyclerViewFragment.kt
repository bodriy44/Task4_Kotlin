package com.example.myapplicationkotlin.view.fragment

import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.view.OnNoteClickListener
import com.example.myapplicationkotlin.adapter.NoteAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplicationkotlin.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.myapplicationkotlin.view.MainActivity

class RecyclerViewFragment : Fragment(R.layout.fragment_recycler),
    com.example.myapplicationkotlin.view.RecyclerView,
    OnNoteClickListener {
    private lateinit var adapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_recycler, container, false)
        adapter = NoteAdapter(this)
        inflate.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView).adapter = adapter
        inflate.findViewById<FloatingActionButton>(R.id.floatingActionButtonAddNote).setOnClickListener { v: View? -> createNote() }
        return inflate
    }

    override fun onStart() {
        super.onStart()
        adapter.setNotes(notes)
    }

    override var notes: List<Note>
        get() = (activity as MainActivity).presenter.getNotes()
        set(notes) {
            adapter.setNotes(notes)
            adapter.notifyDataSetChanged()
        }

    override fun createNote() {
        (activity as MainActivity).presenter.createNote()
    }

    override fun onNoteClick(index: Int) {
        (activity as MainActivity).presenter.showNote(index)
    }
}