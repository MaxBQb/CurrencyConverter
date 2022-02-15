package lab.maxb.currency_converter.dependencyInjection

import lab.maxb.currency_converter.presentation.repository.implementations.inMemory.ConvertersRepositoryImpl
import lab.maxb.currency_converter.presentation.repository.implementations.inMemory.CurrencyRepositoryImpl
import lab.maxb.currency_converter.presentation.repository.interfaces.ConvertersRepository
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository
import org.koin.dsl.module

internal val MODULE_repository = module {
    single<CurrencyRepository> { CurrencyRepositoryImpl() }
    single<ConvertersRepository> { ConvertersRepositoryImpl() }
}