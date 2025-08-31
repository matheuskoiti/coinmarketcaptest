package com.studiomk.exchangelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiomk.domain.result.Result
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
            getListExchangeUseCase().let { result ->
                when(result) {
                    is Result.Success -> {
                        _uiState.value = ExchangeState.ExchangeLoadedState(
                            exchangeList = result.data
                        )
                    }
                    is Result.Error -> {
                        _uiState.value = ExchangeState.ExchangeLoadError(
                            errorMessage = result.error
                        )
                    }
                }
            }
        }
    }
}