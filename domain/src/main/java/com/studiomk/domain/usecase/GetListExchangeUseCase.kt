package com.studiomk.domain.usecase

import com.studiomk.data.RequestResult
import com.studiomk.data.model.ApiResponse
import com.studiomk.data.repository.ExchangeRepository
import com.studiomk.domain.extensions.formatDate
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.domain.result.Result
import java.text.NumberFormat
import java.util.Locale

class GetListExchangeUseCase(
    private val exchangeRepository: ExchangeRepository
) {

    suspend operator fun invoke(): Result<List<ExchangeUi>> {
        return when (val result = exchangeRepository.getExchangeList()) {
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

    private fun createExchangeList(result: RequestResult.Success<ApiResponse>): List<ExchangeUi> {
        return result.data.data.map { (key, exchange) ->
            ExchangeUi(
                id = key,
                name = exchange.name,
                logo = exchange.logo,
                spotVolumeUsd = NumberFormat.getCurrencyInstance(Locale.US)
                    .format(exchange.spotVolumeUsd),
                dateLaunched = exchange.dateLaunched?.formatDate() ?: "",
                description = exchange.description ?: "",
                url = exchange.urls.toString(), // map urls to readable,
                takerFee = exchange.takerFee.toString(), // fix
                makerFee = exchange.makerFee.toString() // fix
            )
        }
    }

}