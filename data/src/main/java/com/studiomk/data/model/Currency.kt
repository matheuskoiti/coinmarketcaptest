package com.studiomk.data.model

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("crypto_id")
    val cryptoId: Int,
    @SerializedName("price_usd")
    val priceUsd: Double,
    val symbol: String,
    val name: String
)
