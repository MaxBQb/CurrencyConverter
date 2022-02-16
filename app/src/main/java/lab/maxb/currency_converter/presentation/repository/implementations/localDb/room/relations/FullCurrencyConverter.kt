package lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.domain.model.CurrencyConverter
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.model.CurrencyConverterDTO
import lab.maxb.currency_converter.presentation.repository.implementations.localDb.room.model.CurrencyDTO


class FullCurrencyConverter(
    @Embedded val currencyConverter: CurrencyConverterDTO,
    @Relation(
        parentColumn = "fromCode",
        entityColumn = "code",
        entity = CurrencyDTO::class
    )
    val from: Currency,
    @Relation(
        parentColumn = "toCode",
        entityColumn = "code",
        entity = CurrencyDTO::class
    )
    val to: Currency,
) {
    fun toBaseModel() = CurrencyConverter(from, to, currencyConverter.rate)
}
