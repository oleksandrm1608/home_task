package com.kefire.revoluthometask.data.server

import android.util.Log
import com.kefire.revoluthometask.domain.entity.Currency
import com.kefire.revoluthometask.domain.entity.Currency.*
import com.kefire.revoluthometask.domain.entity.Type
import com.kefire.revoluthometask.domain.error.ServerConnectionError
import com.kefire.revoluthometask.domain.error.ServerError
import com.kefire.revoluthometask.domain.repository.RateRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(
    private val rateService: RateService
) : RateRepository {

    override suspend fun getRates(): List<Currency> = try {
        val response = rateService.getRates("EUR").await()

        arrayListOf<Currency>().apply {
            add(Currency(Type.EUR, 1.0, "EUR", "Euro"))
            response.rates?.aud?.let { add(Currency(Type.AUD, it, "AUD", "Australian Dollar")) }
            response.rates?.bgn?.let { add(Currency(Type.BGN, it, "BGN", "Bulgarian Lev")) }
            response.rates?.brl?.let { add(Currency(Type.BRL, it, "BRL", "Brazilian Real")) }
            response.rates?.cad?.let { add(Currency(Type.CAD, it, "CAD", "Canadian Dollar")) }
            response.rates?.chf?.let { add(Currency(Type.CHF, it, "CHF", "Swiss Franc")) }
            response.rates?.cny?.let { add(Currency(Type.CNY, it, "CNY", "Renminbi")) }
            response.rates?.czk?.let { add(Currency(Type.CZK, it, "CZK", "Czech Koruna")) }
            response.rates?.dkk?.let { add(Currency(Type.DKK, it, "DKK", "Danish Krone")) }
            response.rates?.gbp?.let { add(Currency(Type.GBP, it, "GBP", "Pound sterling")) }
            response.rates?.hkd?.let { add(Currency(Type.HKD, it, "HKD", "Hong Kong Dollar")) }
            response.rates?.hrk?.let { add(Currency(Type.HRK, it, "HRK", "Croatian Kuna")) }
            response.rates?.huf?.let { add(Currency(Type.HUF, it, "HUF", "Hungarian Forint")) }
            response.rates?.idr?.let { add(Currency(Type.IDR, it, "IDR", "Indonesian Rupiah")) }
            response.rates?.ils?.let { add(Currency(Type.ILS, it, "ILS", "Israeli Shekel")) }
            response.rates?.inr?.let { add(Currency(Type.INR, it, "INR", "Indian Rupee")) }
            response.rates?.isk?.let { add(Currency(Type.ISK, it, "ISK", "Icelandic króna")) }
            response.rates?.jpy?.let { add(Currency(Type.JPY, it, "JPY", "Japanese yen")) }
            response.rates?.krw?.let { add(Currency(Type.KRW, it, "KRW", "South Korean won")) }
            response.rates?.mxn?.let { add(Currency(Type.MXN, it, "MXN", "Mexican Peso")) }
            response.rates?.myr?.let { add(Currency(Type.MYR, it, "MYR", "Malaysian Ringgit")) }
            response.rates?.nok?.let { add(Currency(Type.NOK, it, "NOK", "Norwegian Krone")) }
            response.rates?.nzd?.let { add(Currency(Type.NZD, it, "NZD", "New Zealand Dollar")) }
            response.rates?.php?.let { add(Currency(Type.PHP, it, "PHP", "Philippine Peso")) }
            response.rates?.pln?.let { add(Currency(Type.PLN, it, "PLN", "Polish Złoty")) }
            response.rates?.ron?.let { add(Currency(Type.RON, it, "RON", "Romanian Leu")) }
            response.rates?.rub?.let { add(Currency(Type.RUB, it, "RUB", "Russian Rouble")) }
            response.rates?.sek?.let { add(Currency(Type.SEK, it, "SEK", "Swedish Krona")) }
            response.rates?.sgd?.let { add(Currency(Type.SGD, it, "SGD", "Singapore Dollar")) }
            response.rates?.thb?.let { add(Currency(Type.THB, it, "THB", "Thai Baht")) }
            response.rates?.usd?.let { add(Currency(Type.USD, it, "USD", "US Dollar")) }
            response.rates?.zar?.let { add(Currency(Type.ZAR, it, "ZAR", "South African Rand")) }
        }
    } catch (error: IOException) {
        throw ServerConnectionError()
    } catch (error: HttpException) {
        throw ServerError()
    }
}