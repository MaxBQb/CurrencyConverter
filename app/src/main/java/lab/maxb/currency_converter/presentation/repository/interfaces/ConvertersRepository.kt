package lab.maxb.currency_converter.presentation.repository.interfaces

import kotlinx.coroutines.flow.Flow
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.domain.model.CurrencyConverter

interface ConvertersRepository {
    suspend fun save(converter: CurrencyConverter)
    fun get(from: Currency, to: Currency): Flow<CurrencyConverter?>
}
