package com.studiomk.data.model

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("crypto_id")
    val cryptoId: Int,
    val symbol: String,
    val name: String
)
