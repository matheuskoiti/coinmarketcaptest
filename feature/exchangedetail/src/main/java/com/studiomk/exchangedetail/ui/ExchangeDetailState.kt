package com.studiomk.exchangedetail.ui

import com.studiomk.domain.model.ExchangeAssetUi

sealed class ExchangeDetailState {

    data class ExchangeDetailLoadedState(
        val assets: ExchangeAssetUi
    ): ExchangeDetailState()
    data class ExchangeLoadError(
        val errorMessage: String
    ): ExchangeDetailState()
    data object ExchangeLoading: ExchangeDetailState()
}