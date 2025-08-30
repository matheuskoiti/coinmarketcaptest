package com.studiomk.data.di

import com.studiomk.data.repository.ExchangeRepository
import com.studiomk.data.service.ExchangeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ExchangeService> { get<Retrofit>().create(ExchangeService::class.java) }

    factory { ExchangeRepository(get()) }
}
