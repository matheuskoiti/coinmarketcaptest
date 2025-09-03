package com.studiomk.domain.usecase

import com.studiomk.data.RequestResult
import com.studiomk.data.model.WalletData
import com.studiomk.data.repository.ExchangeRepository
import com.studiomk.domain.extensions.mapToCurrency
import com.studiomk.domain.model.Currency
import com.studiomk.domain.model.ExchangeAssetUi
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.domain.result.Result

class GetExchangeDetailsUseCase(
    private val repository: ExchangeRepository,
    private val getListExchangeUseCase: GetListExchangeUseCase
) {

    suspend operator fun invoke(id: String): Result<ExchangeAssetUi> {
        return when (val listResult = getListExchangeUseCase.invoke(id)) {
            is Result.Error -> {
                Result.Error("Network error: ${listResult.error}")
            }
            is Result.Success -> {
                getExchangeAssets(id, listResult.data.first())
            }
        }
    }

    private suspend fun getExchangeAssets(id: String, exchangeUi: ExchangeUi): Result<ExchangeAssetUi> {
        return when (val result = repository.getExchangeAssets(id)) {
            is RequestResult.Success -> {
                try {
                    Result.Success(createExchangeAsset(result.data.data, exchangeUi))
                } catch (e: Exception) {
                    Result.Error("Error creating exchange detail: ${e.message}")
                }
            }

            is RequestResult.Error -> {
                Result.Error("Network error: ${result.error}")
            }
        }
    }

    private fun createExchangeAsset(data: List<WalletData>, exchangeUi: ExchangeUi): ExchangeAssetUi {
        return ExchangeAssetUi(
            exchangeUi = exchangeUi,
            currencyList = data.map {
                Currency(it.currency.name, it.currency.priceUsd.mapToCurrency())
            }
        )
    }

}