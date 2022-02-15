package lab.maxb.currency_converter.presentation.repository.interfaces

import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.domain.model.CurrencyConverter

interface ConvertersRepository {
    fun getConverter(from: Currency, to: Currency): CurrencyConverter?
}
