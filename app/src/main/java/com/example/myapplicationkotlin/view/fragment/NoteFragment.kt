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
import com.example.myapplicationkotlin.view.NoteView
import com.example.myapplicationkotlin.view.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class NoteFragment : Fragment(R.layout.fragment_note), NoteView {
    lateinit var note: Note
    private lateinit var adapter: PagerAdapter
    private lateinit var viewPager: ViewPager2

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
                (activity as MainActivity).presenter.getNotes()[(viewPager.currentItem + adapter.position2) % adapter.size]
            )
        }
        requireActivity().findViewById<FloatingActionButton>(R.id.floatingActionButtonShare).setOnClickListener { v: View? -> shareNote() }

        adapter = PagerAdapter(this)
        adapter.position2 =  (activity as MainActivity).presenter.getIndexNote(note)
        adapter.size = (activity as MainActivity).presenter.getSize()
        viewPager = requireActivity().findViewById(R.id.pager)
        viewPager.adapter = adapter
        viewPager.isSaveEnabled = false

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun shareNote() {
        val note = (activity as MainActivity).presenter.getNotes()[(viewPager.currentItem + adapter.position2) % adapter.size]
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

    fun deleteNote(note: Note) {
        lifecycleScope.launch {
            (activity as MainActivity).presenter.deleteNote(note)
        }
    }
}