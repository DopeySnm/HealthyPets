package dev.unit6.healthypets.di

import dagger.Module
import dev.unit6.healthypets.di.modules.AppBindsModule
import dev.unit6.healthypets.di.modules.DataBaseModule
import dev.unit6.healthypets.di.modules.ViewModelModule

@Module(
    includes = [
        ViewModelModule::class,
        AppBindsModule::class,
        DataBaseModule::class
    ]
)
class AppModule
