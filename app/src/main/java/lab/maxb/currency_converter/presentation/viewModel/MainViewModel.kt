package lab.maxb.currency_converter.presentation.viewModel

import androidx.databinding.Bindable
import androidx.lifecycle.liveData
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
        set(value) {
            if (field != value) {
                field = value
                convert(true)
            }
        }

    val availableCurrencies = liveData {
        emit(currencyRepository.availableCurrencies)
    }

    private fun convertCurrency(from: Currency, to: Currency, value: Double) =
        (convertersRepository.getConverter(from, to)
            ?: NO_CONVERTER_FOUND).convert(value)

    private fun convert(backwards: Boolean = false)
        = if (backwards) {
            incomeCurrencyAmount = convertCurrency(
                outcomeCurrency, incomeCurrency, outcomeCurrencyAmount)
            notifyPropertyChanged(BR.incomeCurrencyAmount)
        } else {
            outcomeCurrencyAmount = convertCurrency(
                incomeCurrency, outcomeCurrency, incomeCurrencyAmount)
            notifyPropertyChanged(BR.outcomeCurrencyAmount)
        }

    companion object {
        val NO_CURRENCY_SELECTED = Currency("", "")
        val NO_CONVERTER_FOUND = CurrencyConverter(
            NO_CURRENCY_SELECTED,
            NO_CURRENCY_SELECTED,
            1.0
        )
    }
}