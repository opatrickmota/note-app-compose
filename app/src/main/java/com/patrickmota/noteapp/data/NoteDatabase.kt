package com.patrickmota.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patrickmota.noteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}