package lab.maxb.currency_converter.presentation.repository.interfaces

import kotlinx.coroutines.flow.Flow
import lab.maxb.currency_converter.domain.model.Currency

interface CurrencyRepository {
    val availableCurrencies: Flow<List<Currency>>
    fun getAvailableConversionOptions(from: Currency): Flow<List<Currency>>
    suspend fun saveAll(currencies: List<Currency>)
}
