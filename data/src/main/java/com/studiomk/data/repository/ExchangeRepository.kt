package com.studiomk.data.repository

import com.studiomk.data.model.ApiResponse
import com.studiomk.data.RequestResult
import com.studiomk.data.service.ExchangeService

class ExchangeRepository(
    private val exchangeService: ExchangeService
) {

    suspend fun getExchangeList(): RequestResult<ApiResponse> {
        try {
            val key = "here"
            val id = "270,12"
            return RequestResult.Success(exchangeService.geExchangeList(key, id))
        } catch (e: Exception){
            return RequestResult.Error(e.message ?: "An error has occurred")
        }
    }
}