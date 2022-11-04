package com.patrickmota.noteapp.di

import android.app.Application
import androidx.room.Room
import com.patrickmota.noteapp.data.NoteDatabase
import com.patrickmota.noteapp.data.NoteDatabaseDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    fun provideDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(application, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(noteDatabase: NoteDatabase): NoteDatabaseDao {
        return noteDatabase.noteDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}