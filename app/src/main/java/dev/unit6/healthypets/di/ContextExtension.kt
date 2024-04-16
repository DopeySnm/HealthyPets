package dev.unit6.healthypets.di

import android.content.Context
import dev.unit6.healthypets.MainApp

val Context.appComponent: AppComponent
    get() = when(this){
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }
