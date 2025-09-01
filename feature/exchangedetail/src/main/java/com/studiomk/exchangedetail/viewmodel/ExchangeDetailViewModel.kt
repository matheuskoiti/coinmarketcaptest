package com.studiomk.exchangedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.domain.result.Result
import com.studiomk.domain.usecase.GetListExchangeUseCase
import com.studiomk.exchangedetail.ui.ExchangeDetailState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeDetailViewModel(
    private val getListExchangeUseCase: GetListExchangeUseCase
): ViewModel() {


    private val _uiState = MutableStateFlow<ExchangeDetailState>(ExchangeDetailState.ExchangeLoading)
    val uiState: StateFlow<ExchangeDetailState> = _uiState.asStateFlow()

    fun loadDetails(id: String) {
        _uiState.value = ExchangeDetailState.ExchangeLoading
        viewModelScope.launch(Dispatchers.IO) {
            getListExchangeUseCase(id).let { result ->
                when(result) {
                    is Result.Error -> {
                        // TODO
                    }
                    is Result.Success -> {
                        _uiState.value = ExchangeDetailState.ExchangeDetailLoadedState(
                            exchange = result.data.first()
                        )
                    }
                }
            }
        }
    }
}