package com.patrickmota.noteapp.repository

import com.patrickmota.noteapp.data.NoteDatabaseDao
import com.patrickmota.noteapp.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class NoteRepositoryImpl(
    private val noteDatabaseDao: NoteDatabaseDao,
    private val dispatcher: CoroutineDispatcher
) : NoteRepository {
    override suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)
    override suspend fun updateNote(note: Note) = noteDatabaseDao.update(note)
    override suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
    override suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    override fun getAllNotes(): Flow<List<Note>> =
        noteDatabaseDao.getNotes().flowOn(dispatcher).conflate()
}