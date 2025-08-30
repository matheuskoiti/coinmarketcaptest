package com.studiomk.data.service

import com.studiomk.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ExchangeService {

    @GET("/v1/exchange/info")
    suspend fun geExchangeList(
        @Header("X-CMC_PRO_API_KEY") key: String,
        @Query("id") id: String,
    ): ApiResponse
}