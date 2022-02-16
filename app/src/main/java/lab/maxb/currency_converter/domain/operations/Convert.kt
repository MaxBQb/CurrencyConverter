package lab.maxb.currency_converter.domain.operations


import lab.maxb.currency_converter.domain.model.CurrencyConverter

fun CurrencyConverter.convert(amount: Double)
    = this.conversionRate * amount
