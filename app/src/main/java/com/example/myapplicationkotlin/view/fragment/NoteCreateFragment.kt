package com.example.myapplicationkotlin.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.view.INoteCreateFragment
import com.example.myapplicationkotlin.view.MainActivity
import kotlinx.coroutines.launch
import java.util.*

class NoteCreateFragment : Fragment(R.layout.fragment_note_create), INoteCreateFragment {

    // Создаем view который будет содержимым фрагмента и отдаем его системе
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_create, container, false)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<View>(R.id.saveButton).setOnClickListener { v: View? -> addNote() }
        (requireActivity().findViewById<View>(R.id.editTextTitle) as EditText).setText("")
        (requireActivity().findViewById<View>(R.id.editTextText) as EditText).setText("")
    }

    override fun addNote() {
        lifecycleScope.launch {
            (activity as MainActivity).presenter.addNote(note.header, note.body)
        }
        (activity as MainActivity).presenter.saveNote(note)
    }

    val note: Note
        get() = Note(
            (requireActivity().findViewById<View>(R.id.editTextTitle) as EditText).text.toString(),
            (requireActivity().findViewById<View>(R.id.editTextText) as EditText).text.toString(),
            Date().toString()
        )
}