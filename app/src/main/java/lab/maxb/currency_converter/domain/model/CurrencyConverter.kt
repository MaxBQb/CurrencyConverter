package lab.maxb.currency_converter.domain.model

class CurrencyConverter(
    val from: Currency,
    val to: Currency,
    val conversionRate: Double,
)