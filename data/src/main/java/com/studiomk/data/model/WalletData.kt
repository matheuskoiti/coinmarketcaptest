package com.studiomk.data.model

import com.google.gson.annotations.SerializedName

data class WalletData(
    @SerializedName("wallet_address")
    val walletAddress: String,
    val balance: Double,
    val platform: Platform,
    val currency: Currency
)
