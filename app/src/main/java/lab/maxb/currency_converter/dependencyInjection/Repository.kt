package lab.maxb.currency_converter.dependencyInjection

import lab.maxb.currency_converter.presentation.repository.implementations.ConvertersRepositoryImpl
import lab.maxb.currency_converter.presentation.repository.implementations.CurrencyRepositoryImpl
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.LocalDatabase
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.NetworkStateListener
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.buildExchangerateService
import lab.maxb.currency_converter.presentation.repository.interfaces.ConvertersRepository
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val MODULE_repository = module {
    single<CurrencyRepository> { CurrencyRepositoryImpl(get(), get(), get()) }
    single<ConvertersRepository> { ConvertersRepositoryImpl(get(), get(), get()) }
    single { LocalDatabase.buildDatabase(androidApplication()) }
    single { buildExchangerateService() }
    single { NetworkStateListener(androidContext()) }
}