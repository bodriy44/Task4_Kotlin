package com.example.myapplicationkotlin.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.view.MainActivity


class PagerFragment : Fragment() {
    var position : Int = 0
    lateinit var note: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            note = (activity as MainActivity).presenter.getNotes()[position]
            view.findViewById<TextView>(R.id.noteTitle).text = note.header
            view.findViewById<TextView>(R.id.noteDate).text = note.date
            view.findViewById<TextView>(R.id.noteText).text = note.body
    }
}