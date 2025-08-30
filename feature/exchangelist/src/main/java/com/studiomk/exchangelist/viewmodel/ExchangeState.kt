package com.studiomk.exchangelist.viewmodel

import com.studiomk.domain.model.ExchangeUi

data class ExchangeState(
    val exchangeList: List<ExchangeUi> = listOf()
)
