package lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI

import lab.maxb.currency_converter.BuildConfig
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.model.CurrencyConverterDTO
import lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI.model.CurrencyHolder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ExchangerateService {
    @GET("pair/{from}/{to}")
    suspend fun getConverter(
        @Path("from") from: String,
        @Path("to") to: String
    ): CurrencyConverterDTO?

    @GET("codes")
    suspend fun getSupportedCurrencies(): CurrencyHolder?
}

fun buildExchangerateService(): ExchangerateService = Retrofit.Builder()
    .baseUrl("https://v6.exchangerate-api.com/v6/${BuildConfig.EXCHANGERATE_API}/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ExchangerateService::class.java)
