package com.kefire.revoluthometask.data.server

import com.kefire.revoluthometask.data.server.response.RatesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RateService {

    @GET("latest")
    fun getRates(@Query("base") base: String): Deferred<RatesResponse>
}