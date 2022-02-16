package lab.maxb.currency_converter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import lab.maxb.currency_converter.R
import lab.maxb.currency_converter.domain.model.Currency
import lab.maxb.currency_converter.domain.model.CurrencyConverter
import lab.maxb.currency_converter.presentation.repository.interfaces.ConvertersRepository
import lab.maxb.currency_converter.presentation.repository.interfaces.CurrencyRepository
import lab.maxb.currency_converter.presentation.view.MainFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val currencyRepository: CurrencyRepository by inject()
    private val convertersRepository: ConvertersRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        prepopulateDB()
    }

    private fun prepopulateDB() = lifecycleScope.launch {
        // TODO(Delete this method, on network connectivity will added)
        val RUB = Currency("RUB","Russian Ruble")
        val USD = Currency("USD","US Dollar")
        currencyRepository.saveAll(listOf(RUB, USD))
        convertersRepository.save(CurrencyConverter(
            RUB, USD, 77.1255
        ))
    }
}