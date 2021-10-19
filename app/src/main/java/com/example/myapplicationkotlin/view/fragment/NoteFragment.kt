package com.example.myapplicationkotlin.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.adapter.PagerAdapter
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.database.AppDatabase
import com.example.myapplicationkotlin.presenter.NoteFragmentPresenter
import com.example.myapplicationkotlin.view.NoteView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class NoteFragment(var db: AppDatabase) : Fragment(R.layout.fragment_note), NoteView {
    lateinit var note: Note
    private lateinit var adapter: PagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var presenter : NoteFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButtonDelete).setOnClickListener { v: View? ->
            deleteNote(
                presenter.getNotes()[(viewPager.currentItem + adapter.position2) % adapter.size]
            )
        }
        requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButtonShare).setOnClickListener { v: View? -> shareNote() }

        adapter = PagerAdapter(this, presenter.getIndexNote(note), presenter.getSize(),  presenter.getNotes())
        viewPager = requireActivity().findViewById(R.id.pager)
        viewPager.adapter = adapter
        viewPager.isSaveEnabled = false

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun shareNote() {
        val note = presenter.getNotes()[(viewPager.currentItem + adapter.position2) % adapter.size]
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "\n${note.date} ${note.header} ${note.body} Отправлено из приложения MyNote"
        )
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, null))
    }

    fun changeNote(note: Note) {
        this.note = note
    }

    fun setData(notes: MutableList<Note>){
        presenter = NoteFragmentPresenter(db, this)
        presenter.setNotes(notes)
    }


    fun deleteNote(note: Note) {
        lifecycleScope.launch {
            presenter.deleteNote(note)
        }
    }
}