package com.studiomk.exchangedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiomk.domain.result.Result
import com.studiomk.domain.usecase.GetExchangeDetailsUseCase
import com.studiomk.exchangedetail.ui.ExchangeDetailState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeDetailViewModel(
    private val getExchangeDetailsUseCase: GetExchangeDetailsUseCase
): ViewModel() {


    private val _uiState = MutableStateFlow<ExchangeDetailState>(ExchangeDetailState.ExchangeLoading)
    val uiState: StateFlow<ExchangeDetailState> = _uiState.asStateFlow()

    fun loadDetails(id: String) {
        _uiState.value = ExchangeDetailState.ExchangeLoading
        viewModelScope.launch(Dispatchers.IO) {
            getExchangeDetailsUseCase(id).let { result ->
                when(result) {
                    is Result.Error -> {
                        _uiState.value = ExchangeDetailState.ExchangeLoadError(
                            errorMessage = result.error
                        )
                    }
                    is Result.Success -> {
                        _uiState.value = ExchangeDetailState.ExchangeDetailLoadedState(
                            assets = result.data,
                        )
                    }
                }
            }
        }
    }
}