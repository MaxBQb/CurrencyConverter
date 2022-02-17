package lab.maxb.currency_converter.presentation.viewModel

import androidx.databinding.Bindable
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import lab.maxb.currency_converter.BR
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.domain.model.CurrencyConverter
import lab.maxb.currency_converter.domain.operations.convert
import lab.maxb.currency_converter.presentation.repository.interfaces.ConvertersRepository
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository

class MainViewModel(
    private val currencyRepository: CurrencyRepository,
    private val convertersRepository: ConvertersRepository,
) : ObservableViewModel() {
    var incomeCurrency: Currency = NO_CURRENCY_SELECTED
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                convert()
                availableConvertOptions =
                    currencyRepository.getAvailableConversionOptions(incomeCurrency).asLiveData()
                notifyPropertyChanged(BR.availableConvertOptions)
            }
        }

    var outcomeCurrency: Currency = NO_CURRENCY_SELECTED
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                convert()
            }
        }

    var incomeCurrencyAmount = 0.0
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                convert()
            }
        }

    var outcomeCurrencyAmount = 0.0
        @Bindable get

    val availableCurrencies = currencyRepository.availableCurrencies.asLiveData()
    var availableConvertOptions =
        currencyRepository.getAvailableConversionOptions(incomeCurrency).asLiveData()
        @Bindable get

    private fun convert() = viewModelScope.launch {
        if (outcomeCurrency == NO_CURRENCY_SELECTED
            || incomeCurrency == NO_CURRENCY_SELECTED)
            return@launch
        outcomeCurrencyAmount = getConverter().convert(incomeCurrencyAmount)
        notifyPropertyChanged(BR.outcomeCurrencyAmount)
    }

    private suspend fun getConverter()
        = if (incomeCurrency == outcomeCurrency)
            NO_CONVERTER_FOUND
        else
            convertersRepository.get(
                incomeCurrency, outcomeCurrency
            ).first() ?: NO_CONVERTER_FOUND


    companion object {
        val NO_CURRENCY_SELECTED = Currency("", "")
        val NO_CONVERTER_FOUND = CurrencyConverter(
            NO_CURRENCY_SELECTED,
            NO_CURRENCY_SELECTED,
            1.0
        )
    }
}