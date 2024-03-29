package dev.unit6.healthypets

import android.app.Application
import dev.unit6.healthypets.di.AppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
