package com.studiomk.exchangedetail.di

import com.studiomk.exchangedetail.viewmodel.ExchangeDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exchangeDetailModule = module {
    viewModel { ExchangeDetailViewModel(get(), get()) }
}