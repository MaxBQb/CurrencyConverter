package lab.maxb.currency_converter.presentation.repository.implementations.local.room.model

import androidx.room.Entity
import lab.maxb.currency_converter.domain.model.Currency

@Entity(tableName = "currencies", primaryKeys = ["code"])
class CurrencyDTO(code: String, name: String) : Currency(code, name)

fun Currency.toDTO() = this as? CurrencyDTO ?: CurrencyDTO(code, name)
fun CurrencyDTO.toBaseModel() = this as Currency
