package lab.maxb.currency_converter.presentation.repository.interfaces

import lab.maxb.currency_converter.domain.model.Currency

interface CurrencyRepository {
    val availableCurrencies: List<Currency>
}
