package com.studiomk.domain.di

import com.studiomk.domain.usecase.GetListExchangeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetListExchangeUseCase(get()) }
}