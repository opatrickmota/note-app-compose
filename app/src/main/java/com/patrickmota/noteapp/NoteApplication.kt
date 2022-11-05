package com.patrickmota.noteapp

import android.app.Application
import com.patrickmota.noteapp.di.databaseModule
import com.patrickmota.noteapp.di.repositoryModule
import com.patrickmota.noteapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NoteApplication)
            modules(databaseModule, repositoryModule, viewModelModule)
        }
    }
}