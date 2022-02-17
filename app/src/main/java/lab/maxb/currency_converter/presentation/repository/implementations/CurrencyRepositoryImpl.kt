package lab.maxb.currency_converter.presentation.repository.implementations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.LocalDatabase
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.model.toDTO
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.ExchangerateService
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository

class CurrencyRepositoryImpl(
    db: LocalDatabase,
    private val service: ExchangerateService
) : CurrencyRepository {
    private val dao = db.currencyDao()

    override val availableCurrencies
        get() = flowOf(
            dao.getAvailable(),
            getSupportedCurrencies()
        ).flatMapLatest { it }

    override fun getAvailableConversionOptions(from: Currency): Flow<List<Currency>>
        = flowOf(
            dao.getAvailableConversionOptions(from.code),
            getSupportedCurrencies()
        ).flatMapLatest { it }

    private fun getSupportedCurrencies() = flow {
        service.getSupportedCurrencies()?.let { holder ->
            saveAll(holder.currencies)
            println("Get all currencies")
            emitAll(dao.getAll())
        }
    }.flowOn(Dispatchers.IO)
        .catch { it.printStackTrace() }

    override suspend fun saveAll(currencies: List<Currency>)
        = dao.saveAll(currencies.map { it.toDTO() })
}