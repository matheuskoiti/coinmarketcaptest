package com.studiomk.domain.model

data class ExchangeAssetUi(
    val exchangeUi: ExchangeUi,
    val currencyList: List<Currency>
)

data class Currency(
    val name: String,
    val priceUsd: String
)