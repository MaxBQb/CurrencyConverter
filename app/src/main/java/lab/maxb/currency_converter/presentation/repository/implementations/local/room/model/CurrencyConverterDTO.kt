package lab.maxb.currency_converter.presentation.repository.implementations.local.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import lab.maxb.currency_converter.domain.model.CurrencyConverter

@Entity(tableName = "converters",
        primaryKeys = ["fromCode", "toCode"],
        foreignKeys = [
            ForeignKey(
                entity = CurrencyDTO::class,
                parentColumns = ["code"],
                childColumns = ["fromCode"],
                onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                entity = CurrencyDTO::class,
                parentColumns = ["code"],
                childColumns = ["toCode"],
                onDelete = ForeignKey.CASCADE
            )
        ]
)
class CurrencyConverterDTO(
    var fromCode: String,
    var toCode: String,
    var rate: Double
)

fun CurrencyConverter.toDTO() = CurrencyConverterDTO(from.code, to.code, conversionRate)
