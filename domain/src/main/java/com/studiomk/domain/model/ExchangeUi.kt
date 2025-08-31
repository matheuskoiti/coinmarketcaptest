package com.studiomk.domain.model

data class ExchangeUi(
    val id: String,
    val name: String,
    val logo: String,
    val spotVolumeUsd: String,
    val dateLaunched: String,
    val description: String,
    val url: String,
    val makerFee: String,
    val takerFee: String
)