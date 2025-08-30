package com.studiomk.exchangelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiomk.domain.usecase.GetListExchangeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val getListExchangeUseCase: GetListExchangeUseCase
) : ViewModel() {

    init {
        getExchangeList()
    }

    private val _uiState = MutableStateFlow(ExchangeState())
    val uiState: StateFlow<ExchangeState> = _uiState.asStateFlow()

    private fun getExchangeList() {
        viewModelScope.launch(Dispatchers.IO) {
            getListExchangeUseCase.invoke().let { list ->
                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        exchangeList = list
                    )
                }
            }
        }
    }
}