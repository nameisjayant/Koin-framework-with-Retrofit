package com.codingwithjks.koinwithretrofit.container

import android.app.Application
import com.codingwithjks.koinwithretrofit.di.appModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}