package com.sevenpeakssoftware.faraz

import android.app.Application
import com.sevenpeakssoftware.faraz.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CarsApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@CarsApplication)
            modules(AppModule + NetworkModule + LocalModule + featureModules())
        }
    }
}
