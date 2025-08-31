package com.studiomk.exchangelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiomk.domain.usecase.GetListExchangeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val getListExchangeUseCase: GetListExchangeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExchangeState>(ExchangeState.ExchangeLoading)
    val uiState: StateFlow<ExchangeState> = _uiState.asStateFlow()

    init {
        getExchangeList()
    }

    fun getExchangeList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = ExchangeState.ExchangeLoading
            try {
                getListExchangeUseCase.invoke().let { list ->
                    _uiState.value = ExchangeState.ExchangeLoadedState(
                        exchangeList = list
                    )
                }
            } catch (e: Exception) {
                _uiState.value = ExchangeState.ExchangeLoadError(
                    errorMessage = e.message ?: "An error occurred"
                )
            }

        }
    }
}