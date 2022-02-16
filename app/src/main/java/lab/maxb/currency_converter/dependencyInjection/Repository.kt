package lab.maxb.currency_converter.dependencyInjection

import lab.maxb.currency_converter.presentation.repository.implementations.localDb.ConvertersRepositoryImpl
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.CurrencyRepositoryImpl
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.LocalDatabase
import lab.maxb.currency_converter.presentation.repository.interfaces.ConvertersRepository
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal val MODULE_repository = module {
    single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
    single<ConvertersRepository> { ConvertersRepositoryImpl(get()) }
    single { LocalDatabase.buildDatabase(androidApplication()) }
}