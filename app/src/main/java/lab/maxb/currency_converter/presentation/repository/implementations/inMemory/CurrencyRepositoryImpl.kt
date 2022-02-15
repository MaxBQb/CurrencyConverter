package lab.maxb.currency_converter.presentation.repository.implementations.inMemory

import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository

class CurrencyRepositoryImpl : CurrencyRepository {
    override val availableCurrencies: List<Currency> = listOf(
        Currency("RUB","Russian Ruble"),
        Currency("USD","US Dollar"),
        Currency("AED","UAE Dirham"),
        Currency("AFN","Afghan Afghani"),
        Currency("ALL","Albanian Lek"),
        Currency("AMD","Armenian Dram"),
        Currency("ANG","Netherlands Antillian Guilder"),
        Currency("AOA","Angolan Kwanza"),
    )
}