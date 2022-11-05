package com.patrickmota.noteapp.repository

import com.patrickmota.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun deleteAllNotes()
    fun getAllNotes(): Flow<List<Note>>
}