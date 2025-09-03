package com.studiomk.domain.usecase

import com.studiomk.data.RequestResult
import com.studiomk.data.model.ApiResponse
import com.studiomk.data.model.Exchange
import com.studiomk.data.model.Urls
import com.studiomk.data.repository.ExchangeRepository
import com.studiomk.domain.extensions.formatDate
import com.studiomk.domain.extensions.mapToCurrency
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.domain.result.Result
import java.text.NumberFormat
import java.util.Locale

class GetListExchangeUseCase(
    private val exchangeRepository: ExchangeRepository
) {

    suspend operator fun invoke(id: String? = null): Result<List<ExchangeUi>> {
        return when (val result = exchangeRepository.getExchangeList(id)) {
            is RequestResult.Success -> {
                try {
                    Result.Success(createExchangeList(result))
                } catch (e: Exception) {
                    Result.Error("Error creating exchange list: ${e.message}")
                }
            }
            is RequestResult.Error -> {
                Result.Error("Network error: ${result.error}")
            }
        }
    }

    private fun createExchangeList(result: RequestResult.Success<ApiResponse<Map<String, Exchange>>>): List<ExchangeUi> {
        return result.data.data.map { (key, exchange) ->
            ExchangeUi(
                id = key,
                name = exchange.name,
                logo = exchange.logo,
                spotVolumeUsd = exchange.spotVolumeUsd.mapToCurrency(),
                dateLaunched = exchange.dateLaunched?.formatDate() ?: "",
                description = exchange.description ?: "",
                url = extractWebsiteUrl(exchange.urls),
                takerFee = exchange.takerFee.mapToCurrency(),
                makerFee = exchange.makerFee.mapToCurrency()
            )
        }
    }

    private fun extractWebsiteUrl(urls: Urls): String {
        return urls.website.joinToString(",")
    }
}