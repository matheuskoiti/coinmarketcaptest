package com.studiomk.exchangelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiomk.domain.usecase.GetListExchangeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val getListExchangeUseCase: GetListExchangeUseCase
) : ViewModel() {

    fun getExchangeList() {
        viewModelScope.launch(Dispatchers.IO) {
            getListExchangeUseCase.invoke().let {
                // sendo to ui
            }
        }
    }
}