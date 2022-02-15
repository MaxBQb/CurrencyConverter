package lab.maxb.currency_converter.domain.operations


import lab.maxb.currency_converter.domain.model.CurrencyConverter

fun CurrencyConverter.convert(amount: Double)
    = this.conversionRate * amount

val CurrencyConverter.reversed get()
    = CurrencyConverter(to, from, 1.0/conversionRate)
