package com.example.myapplicationkotlin.view.fragment

import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.view.OnNoteClickListener
import com.example.myapplicationkotlin.adapter.NoteAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.database.AppDatabase
import com.example.myapplicationkotlin.presenter.NoteFragmentPresenter
import com.example.myapplicationkotlin.presenter.RecyclerViewPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.myapplicationkotlin.view.MainActivity
import kotlinx.coroutines.launch
import java.sql.Types.NULL

class RecyclerViewFragment(var db: AppDatabase) : Fragment(R.layout.fragment_recycler),
    com.example.myapplicationkotlin.view.RecyclerView,
    OnNoteClickListener {
    private lateinit var presenter: RecyclerViewPresenter
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = RecyclerViewPresenter(db, activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_recycler, container, false)
        adapter = NoteAdapter(this)
        inflate.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView).adapter =
            adapter
        inflate.findViewById<FloatingActionButton>(R.id.floatingActionButtonAddNote)
            .setOnClickListener { v: View? -> createNote() }
        return inflate
    }

    override fun onStart() {
        super.onStart()
        adapter.setNotes(presenter.getNotes())
    }

    override fun createNote() {
        presenter.createNote()
    }

    override fun onNoteClick(index: Int) {
        presenter.showNote(index)
    }

    fun setData(notes: MutableList<Note>){
        Log.i("notes", "4")
        presenter = RecyclerViewPresenter(db, activity as MainActivity)
        presenter.setNotes(notes)
    }

    fun initAdapter(notes: MutableList<Note>){
        adapter.setNotes(notes)
        adapter.notifyDataSetChanged()
    }
}
