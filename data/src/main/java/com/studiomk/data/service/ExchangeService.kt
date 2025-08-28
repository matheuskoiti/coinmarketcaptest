package com.studiomk.data.service

import com.studiomk.data.model.Exchange
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService {

    @GET("/users")
    suspend fun geExchangeList(
        @Query("per_page") perPage: Int,
    ): List<Exchange>
}