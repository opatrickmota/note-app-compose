package com.patrickmota.noteapp.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrickmota.noteapp.model.Note
import com.patrickmota.noteapp.repository.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: NoteRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNote ->
                if (listOfNote.isEmpty().not()) {
                    _noteList.value = listOfNote
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

}