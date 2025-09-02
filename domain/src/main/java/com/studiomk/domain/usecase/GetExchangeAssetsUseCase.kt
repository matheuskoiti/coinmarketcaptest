package com.studiomk.domain.usecase

import com.studiomk.data.RequestResult
import com.studiomk.data.model.WalletData
import com.studiomk.data.repository.ExchangeRepository
import com.studiomk.domain.model.Currency
import com.studiomk.domain.model.ExchangeAssetUi
import com.studiomk.domain.result.Result

class GetExchangeAssetsUseCase(
    private val repository: ExchangeRepository,
) {

    suspend operator fun invoke(id: String): Result<ExchangeAssetUi> {
        return when (val result = repository.getExchangeAssets(id)) {
            is RequestResult.Success -> {
                try {
                    Result.Success(createExchangeAsset(result.data.data))
                } catch (e: Exception) {
                    Result.Error("Error creating exchange detail: ${e.message}")
                }
            }
            is RequestResult.Error -> {
                Result.Error("Network error: ${result.error}")
            }
        }
    }

    private fun createExchangeAsset(data: List<WalletData>): ExchangeAssetUi {
        return ExchangeAssetUi(
            data.map {
                Currency(it.currency.name, it.currency.priceUsd)
            }
        )
    }
}