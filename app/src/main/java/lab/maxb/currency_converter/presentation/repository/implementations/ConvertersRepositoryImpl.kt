package lab.maxb.currency_converter.presentation.repository.implementations

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.domain.model.CurrencyConverter
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.LocalDatabase
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.model.CurrencyConverterDTO
import lab.maxb.currency_converter.presentation.repository.implementations.local.room.model.toDTO
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.ExchangerateService
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.NetworkStateListener
import lab.maxb.currency_converter.presentation.repository.interfaces.ConvertersRepository

class ConvertersRepositoryImpl(
    db: LocalDatabase,
    private val service: ExchangerateService,
    private val networkStateListener: NetworkStateListener,
): ConvertersRepository {
    private val dao = db.currencyConverterDao()
    override suspend fun save(converter: CurrencyConverter)
        = dao.save(converter.toDTO())

    override fun get(from: Currency, to: Currency)
        = flow {
            refreshConverter(from.code, to.code)
            emitAll(dao.get(from.code, to.code).map { it?.toBaseModel() })
        }

    private suspend fun refreshConverter(from: String, to: String) {
        try {
            if (!networkStateListener.isConnected)
                return
            service.getConverter(from, to)?.let {
                dao.save(
                    CurrencyConverterDTO(
                        it.base_code,
                        it.target_code,
                        it.conversion_rate,
                    )
                )
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}