package com.ida.mynotes

import android.app.Application
import com.ida.mynotes.di.appModule
import com.ida.mynotes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    //Umida2000
    override fun onCreate() {
        super.onCreate()
        //koin set up is finished
        startKoin {
            androidContext(this@App)
            modules(appModule, viewModelModule)
        }

    }
}