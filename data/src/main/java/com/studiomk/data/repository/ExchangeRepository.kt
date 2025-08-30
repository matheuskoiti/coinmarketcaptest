package com.studiomk.data.repository

import com.studiomk.data.model.ApiResponse
import com.studiomk.data.service.ExchangeService

class ExchangeRepository(
    private val exchangeService: ExchangeService
) {

    suspend fun getExchangeList(): ApiResponse {
        val key = "keyHere"
        val id = "270,12"
        return exchangeService.geExchangeList(key, id)
    }
}