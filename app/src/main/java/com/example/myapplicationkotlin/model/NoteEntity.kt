package com.example.myapplicationkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "notes")
data class NoteEntity (
    @ColumnInfo(name = "note_title") val title: String,
    @ColumnInfo(name = "note_text") val text: String,
    @ColumnInfo(name = "note_date") val time: String = Date().toString(),
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)