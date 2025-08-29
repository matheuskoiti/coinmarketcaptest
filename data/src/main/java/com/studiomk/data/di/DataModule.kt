package com.studiomk.data.di

import com.studiomk.data.ExchangeRepository
import com.studiomk.data.service.ExchangeService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ExchangeService> { get<Retrofit>().create(ExchangeService::class.java) }

    factory { ExchangeRepository(get()) }
}
