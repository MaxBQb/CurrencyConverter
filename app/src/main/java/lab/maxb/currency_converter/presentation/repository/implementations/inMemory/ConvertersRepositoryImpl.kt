package lab.maxb.currency_converter.presentation.repository.implementations.inMemory

import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.domain.model.CurrencyConverter
import lab.maxb.currency_converter.domain.operations.reversed
import lab.maxb.currency_converter.presentation.repository.interfaces.ConvertersRepository

class ConvertersRepositoryImpl: ConvertersRepository {
    private val converters = listOf(
        CurrencyConverter(
            Currency("USD", "US Dollar"),
            Currency("RUB", "Russian Ruble"),
            77.1255
        )
    )

    override fun getConverter(from: Currency, to: Currency)
        = converters.find {
            it.from.code == from.code &&
            it.to.code == to.code
        }
        ?: converters.find {
            it.from.code == to.code &&
            it.to.code == from.code
        }?.reversed
}