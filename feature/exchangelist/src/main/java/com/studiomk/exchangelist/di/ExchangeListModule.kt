package com.studiomk.exchangelist.di

import com.studiomk.exchangelist.viewmodel.ExchangeListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exchangeListModule = module {
    viewModel { ExchangeListViewModel(get()) }
}