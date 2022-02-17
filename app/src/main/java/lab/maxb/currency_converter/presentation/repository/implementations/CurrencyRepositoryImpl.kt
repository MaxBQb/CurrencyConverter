package lab.maxb.currency_converter.presentation.repository.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.LocalDatabase
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.model.CurrencyDTO
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.model.toDTO
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.ExchangerateService
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.NetworkStateListener
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit

class CurrencyRepositoryImpl(
    db: LocalDatabase,
    private val service: ExchangerateService,
    private val networkStateListener: NetworkStateListener,
) : CurrencyRepository {
    private val dao = db.currencyDao()
    private var lastRequested = 0L

    override val availableCurrencies
        get() = flow {
            emitAll(getSupportedCurrencies() ?: dao.getAvailable())
        }

    override fun getAvailableConversionOptions(from: Currency)
        = flow {
            emitAll(
                getSupportedCurrencies()
                ?: dao.getAvailableConversionOptions(from.code)
            )
        }

    private suspend fun getSupportedCurrencies(): Flow<List<CurrencyDTO>>? {
        if (!networkStateListener.isConnected)
            return null

        if (lastRequested + minRefreshDelay > System.currentTimeMillis())
            return dao.getAll()

        lastRequested = System.currentTimeMillis()

        try {
            service.getSupportedCurrencies()?.let { holder ->
                saveAll(holder.currencies)
                println("Get all currencies")
                return dao.getAll()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }


    override suspend fun saveAll(currencies: List<Currency>)
        = dao.saveAll(currencies.map { it.toDTO() })

    companion object {
        val minRefreshDelay = 1.days.toLong(DurationUnit.MILLISECONDS)
    }
}