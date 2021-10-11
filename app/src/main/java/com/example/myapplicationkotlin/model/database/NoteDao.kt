package com.example.myapplicationkotlin.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.model.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Insert
    suspend fun addNote(note: NoteEntity)
}