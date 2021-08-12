package de.darthkali.weefood

import android.app.Application
import de.darthkali.weefood.android.di.appModule
import de.darthkali.weefood.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent

class BaseApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}



