package com.studiomk.exchangelist.viewmodel

import com.studiomk.domain.model.ExchangeUi

sealed class ExchangeState {

    data class ExchangeLoadedState(
        val exchangeList: List<ExchangeUi> = listOf()
    ): ExchangeState()
    data class ExchangeLoadError(
        val errorMessage: String
    ): ExchangeState()
    data object ExchangeLoading: ExchangeState()
}
