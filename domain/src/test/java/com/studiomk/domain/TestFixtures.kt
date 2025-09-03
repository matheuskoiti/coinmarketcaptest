package com.studiomk.domain

import com.studiomk.data.model.Exchange
import com.studiomk.data.model.Status
import com.studiomk.data.model.Urls

object TestFixtures {

    val mockStatus = Status(
        timestamp = "2025-09-02T12:00:00Z",
        errorCode = 0,
        errorMessage = null,
        elapsed = 123,
        creditCount = 1,
        notice = null
    )

    val mockExchange = Exchange(
        id = 1,
        name = "Binance",
        slug = "binance",
        logo = "https://s2.coinmarketcap.com/static/img/exchanges/64x64/270.png",
        description = "A global cryptocurrency exchange providing a platform for trading various cryptocurrencies.",
        dateLaunched = "2017-07-14",
        notice = null,
        countries = listOf("Cayman Islands", "Seychelles"),
        fiats = listOf("USD", "EUR", "BRL"),
        tags = listOf("Centralized", "Top Exchange"),
        type = "Centralized",
        makerFee = 0.001,
        takerFee = 0.001,
        weeklyVisits = 12500000L,
        spotVolumeUsd = 1456789012.45,
        spotVolumeLastUpdated = "2025-09-01T12:00:00Z",
        urls = Urls(
            website = listOf("https://www.binance.com/"),
            twitter = listOf("https://twitter.com/binance"),
            blog = listOf("https://reddit.com/r/binance"),
            chat = listOf("https://t.me/binanceexchange"),
            fee = listOf("https://t.me/binanceexchange")
        )
    )
}