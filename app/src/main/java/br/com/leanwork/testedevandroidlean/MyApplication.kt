package br.com.leanwork.testedevandroidlean

import android.app.Application
import br.com.leanwork.testedevandroidlean.di.appModules
import org.koin.android.ext.android.startKoin

/**
 * My application
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, modules = appModules)
    }
}