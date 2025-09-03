package com.studiomk.exchangelist.ui.testutils

import com.studiomk.domain.usecase.GetListExchangeUseCase
import com.studiomk.exchangelist.viewmodel.ExchangeListViewModel
import com.studiomk.exchangelist.viewmodel.ExchangeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeExchangeListViewModel(
    getListExchangeUseCase: GetListExchangeUseCase,
    dispatcher: CoroutineDispatcher
) : ExchangeListViewModel(getListExchangeUseCase, dispatcher) {
    private val _uiState = MutableStateFlow<ExchangeState>(ExchangeState.ExchangeLoading)
    override val uiState: StateFlow<ExchangeState> = _uiState

    fun emitState(state: ExchangeState) {
        _uiState.value = state
    }
}