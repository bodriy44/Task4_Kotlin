package com.example.myapplicationkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationkotlin.R
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.view.OnNoteClickListener
import java.util.*

class NoteAdapter(private val onNoteClickListener: OnNoteClickListener) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private var notes: List<Note> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.headerView.text = note.header
        holder.dateView.text = note.date
        holder.itemView.setOnClickListener { v: View? -> onNoteClickListener.onNoteClick(position) }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
    }

    fun getNotes(): List<Note> {
        return this.notes
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateView: TextView
        val headerView: TextView

        init {
            dateView = itemView.findViewById<View>(R.id.date) as TextView
            headerView = itemView.findViewById<View>(R.id.title) as TextView
        }
    }
}