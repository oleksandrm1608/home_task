package com.kefire.revoluthometask.data.server.response

import com.google.gson.annotations.SerializedName

class RatesResponse(
    @SerializedName("baseCurrency") val baseCurrency: String?,
    @SerializedName("rates") val rates: Rates?
)