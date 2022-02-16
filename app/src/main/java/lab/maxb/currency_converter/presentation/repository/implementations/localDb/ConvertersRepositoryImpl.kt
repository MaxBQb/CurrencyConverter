package lab.maxb.currency_converter.presentation.repository.implementations.localDb

import kotlinx.coroutines.flow.map
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.domain.model.CurrencyConverter
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.LocalDatabase
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.model.toDTO
import lab.maxb.currency_converter.presentation.repository.interfaces.ConvertersRepository

class ConvertersRepositoryImpl(db: LocalDatabase): ConvertersRepository {
    private val dao = db.currencyConverterDao()
    override suspend fun save(converter: CurrencyConverter)
        = dao.save(converter.toDTO())

    override fun get(from: Currency, to: Currency)
        = dao.get(from.code, to.code).map { it?.toBaseModel() }
}