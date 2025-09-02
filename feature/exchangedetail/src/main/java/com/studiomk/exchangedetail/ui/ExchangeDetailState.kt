package com.studiomk.exchangedetail.ui

import com.studiomk.domain.model.ExchangeAssetUi
import com.studiomk.domain.model.ExchangeUi

sealed class ExchangeDetailState {

    data class ExchangeDetailLoadedState(
        val exchange: ExchangeUi,
        val assets: ExchangeAssetUi? = null
    ): ExchangeDetailState()
    data class ExchangeLoadError(
        val errorMessage: String
    ): ExchangeDetailState()
    data object ExchangeLoading: ExchangeDetailState()
}