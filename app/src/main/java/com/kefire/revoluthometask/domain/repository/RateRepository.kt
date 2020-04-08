package com.kefire.revoluthometask.domain.repository

import com.kefire.revoluthometask.domain.entity.Currency

interface RateRepository {

    suspend fun getRates(): List<Currency>
}