package com.patrickmota.noteapp

import android.app.Application
import com.patrickmota.noteapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NoteApplication)
            modules(appModule)
        }
    }
}