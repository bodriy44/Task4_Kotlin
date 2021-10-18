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
import com.example.myapplicationkotlin.view.NoteCreateView
import com.example.myapplicationkotlin.view.MainActivity
import kotlinx.coroutines.launch
import java.util.*

class NoteCreateFragment : Fragment(R.layout.fragment_note_create), NoteCreateView {

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
        requireActivity().findViewById<EditText>(R.id.editTextTitle).setText("")
        requireActivity().findViewById<EditText>(R.id.editTextText).setText("")
    }

    override fun addNote() {
        (activity as MainActivity).newNote(note)
    }

    val note: Note
        get() = Note(
            requireActivity().findViewById<EditText>(R.id.editTextTitle).text.toString(),
            requireActivity().findViewById<EditText>(R.id.editTextText).text.toString(),
            Date().toString()
        )
}