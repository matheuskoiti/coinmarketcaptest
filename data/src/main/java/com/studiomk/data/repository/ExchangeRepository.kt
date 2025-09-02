package com.studiomk.data.repository

import com.studiomk.data.model.ApiResponse
import com.studiomk.data.RequestResult
import com.studiomk.data.model.Exchange
import com.studiomk.data.model.WalletData
import com.studiomk.data.service.ExchangeService

private const val FULL_ID_LIST = "270,300,350,400,280,290"
private const val key = "90d7c8c3-5578-4174-8fa2-2e8f81159038"
class ExchangeRepository(
    private val exchangeService: ExchangeService
) {

    suspend fun getExchangeList(
        id: String?
    ): RequestResult<ApiResponse<Map<String, Exchange>>> {
        return try {
            RequestResult.Success(exchangeService.geExchangeList(key, id ?: FULL_ID_LIST))
        } catch (e: Exception){
            RequestResult.Error(e.message ?: "An error has occurred")
        }
    }

    suspend fun getExchangeAssets(
        id: String
    ): RequestResult<ApiResponse<List<WalletData>>> {
        return try {
            RequestResult.Success(exchangeService.geExchangeAssets(key, id))
        } catch (e: Exception){
            RequestResult.Error(e.message ?: "An error has occurred")
        }
    }
}