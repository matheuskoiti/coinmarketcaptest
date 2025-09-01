package com.studiomk.data.repository

import com.studiomk.data.model.ApiResponse
import com.studiomk.data.RequestResult
import com.studiomk.data.service.ExchangeService

private const val FULL_ID_LIST = "270,300,350,400,280,290"

class ExchangeRepository(
    private val exchangeService: ExchangeService
) {

    suspend fun getExchangeList(
        id: String?
    ): RequestResult<ApiResponse> {
        try {
            val key = "here"
            return RequestResult.Success(exchangeService.geExchangeList(key, id ?: FULL_ID_LIST))
        } catch (e: Exception){
            return RequestResult.Error(e.message ?: "An error has occurred")
        }
    }
}