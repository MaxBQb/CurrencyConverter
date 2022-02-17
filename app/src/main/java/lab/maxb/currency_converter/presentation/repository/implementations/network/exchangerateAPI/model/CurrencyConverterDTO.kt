package lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.model

class CurrencyConverterDTO(
    var base_code: String,
    var target_code: String,
    var conversion_rate: Double,
)
