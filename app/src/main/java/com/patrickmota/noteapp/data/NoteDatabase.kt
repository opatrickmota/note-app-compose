package com.patrickmota.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.patrickmota.noteapp.model.Note
import com.patrickmota.noteapp.util.DateConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}