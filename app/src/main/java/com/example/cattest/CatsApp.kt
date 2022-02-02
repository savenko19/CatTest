package com.example.cattest

import android.app.Application
import com.example.cattest.di.AppComponent
import com.example.cattest.di.DaggerAppComponent
import com.example.cattest.di.LocalModule
import com.example.cattest.di.RemoteModule

class CatsApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .localModule(LocalModule(this))
            .remoteModule(RemoteModule())
            .build()
    }
}