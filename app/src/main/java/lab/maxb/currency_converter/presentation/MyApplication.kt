package lab.maxb.currency_converter.presentation

import android.app.Application
import lab.maxb.currency_converter.dependencyInjection.MODULE_repository
import lab.maxb.currency_converter.dependencyInjection.MODULE_viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Suppress("unused") // Used by AndroidManifest.xml
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                MODULE_repository,
                MODULE_viewModels,
            )
        }
    }
}