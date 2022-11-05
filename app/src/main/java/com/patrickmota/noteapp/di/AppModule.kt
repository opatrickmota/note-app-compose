package com.patrickmota.noteapp.di

import android.app.Application
import androidx.room.Room
import com.patrickmota.noteapp.data.NoteDatabase
import com.patrickmota.noteapp.data.NoteDatabaseDao
import com.patrickmota.noteapp.repository.NoteRepository
import com.patrickmota.noteapp.repository.NoteRepositoryImpl
import com.patrickmota.noteapp.screen.NoteViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {
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

val repositoryModule = module {
    single(named("IODispatcher")) { Dispatchers.IO }
    single <NoteRepository> {
        NoteRepositoryImpl(get(), get(named("IODispatcher")))
    }
}

val viewModelModule = module {
    single {
        NoteViewModel(get(), get(named("IODispatcher")))
    }
}