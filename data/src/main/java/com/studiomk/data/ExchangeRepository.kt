package com.studiomk.data

import com.studiomk.data.model.ApiResponse
import com.studiomk.data.service.ExchangeService

class ExchangeRepository(
    private val exchangeService: ExchangeService
) {

    suspend fun getExchangeList(): ApiResponse {
        return exchangeService.geExchangeList("keyInHere")
    }
}