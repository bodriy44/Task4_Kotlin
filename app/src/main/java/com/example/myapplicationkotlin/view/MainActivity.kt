package com.example.myapplicationkotlin.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.database.AppDatabase
import com.example.myapplicationkotlin.presenter.MainPresenter
import com.example.myapplicationkotlin.view.fragment.DialogAboutFragment
import com.example.myapplicationkotlin.view.fragment.NoteCreateFragment
import com.example.myapplicationkotlin.view.fragment.NoteFragment
import com.example.myapplicationkotlin.view.fragment.RecyclerViewFragment
import kotlinx.coroutines.launch


class MainActivity : FragmentActivity(), IMainView {
    lateinit var presenter: MainPresenter
    private lateinit var noteCreateFragment: NoteCreateFragment
    lateinit var noteFragment: NoteFragment
    lateinit var recyclerViewFragment: RecyclerViewFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.button_about).setOnClickListener { v: View? ->
            val myDialogFragment = DialogAboutFragment()
            myDialogFragment.show(supportFragmentManager.beginTransaction(), "dialog")
        }
        presenter = MainPresenter(this, AppDatabase.getDatabase(this))
        noteCreateFragment = NoteCreateFragment()
        noteFragment = NoteFragment()
        recyclerViewFragment = RecyclerViewFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, recyclerViewFragment)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            presenter.setNotes(presenter.getAllNotes() as MutableList<Note>)
            recyclerViewFragment.notes = (presenter.getAllNotes() as MutableList<Note>)
        }
    }

    override fun showCreateFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, noteCreateFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showNote(note: Note) {
        noteFragment.changeNote(note)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, noteFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showRecycler() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, recyclerViewFragment)
            .commit()
    }
}