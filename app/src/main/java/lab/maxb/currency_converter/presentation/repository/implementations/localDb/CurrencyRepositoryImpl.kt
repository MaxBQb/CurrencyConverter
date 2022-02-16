package lab.maxb.currency_converter.presentation.repository.implementations.localDb

import kotlinx.coroutines.flow.Flow
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.LocalDatabase
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.model.toDTO
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository

class CurrencyRepositoryImpl(db: LocalDatabase) : CurrencyRepository {
    private val dao = db.currencyDao()

    override val availableCurrencies
        get() = dao.getAvailable()

    override fun getAvailableConversionOptions(from: Currency): Flow<List<Currency>>
        = dao.getAvailableConversionOptions(from.code)

    override suspend fun saveAll(currencies: List<Currency>)
        = dao.saveAll(currencies.map { it.toDTO() })
}