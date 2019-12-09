package ru.shepetov.navicotest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.shepetov.navicotest.di.commonModule
import ru.shepetov.navicotest.di.networkModule
import ru.shepetov.navicotest.di.storageModule

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(listOf( networkModule, storageModule, commonModule))
        }
    }
}