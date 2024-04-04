package dev.unit6.healthypets

import android.app.Application
import dev.unit6.healthypets.di.AppComponent
import dev.unit6.healthypets.di.DaggerAppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
