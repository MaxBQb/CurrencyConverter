package lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.model

import lab.maxb.currency_converter.domain.model.Currency

class CurrencyHolder(
    var supported_codes: List<List<String>>
) {
    val currencies get()
        = supported_codes.map { Currency(it[0], it[1]) }
}