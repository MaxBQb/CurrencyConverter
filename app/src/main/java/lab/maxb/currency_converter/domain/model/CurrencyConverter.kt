package lab.maxb.currency_converter.domain.model

open class CurrencyConverter(
    open val from: Currency,
    open val to: Currency,
    open val conversionRate: Double,
)