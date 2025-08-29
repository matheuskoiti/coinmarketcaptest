package com.studiomk.data.service

import com.studiomk.data.model.Exchange
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService {

    @GET("/v1/exchange/info")
    suspend fun geExchangeList(
        @Query("CMC_PRO_API_KEY") key: Int,
    ): List<Exchange>
}