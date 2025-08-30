package com.studiomk.domain.usecase

import com.studiomk.data.repository.ExchangeRepository
import com.studiomk.domain.extensions.formatDate
import com.studiomk.domain.model.ExchangeUi
import java.text.NumberFormat
import java.util.Locale

class GetListExchangeUseCase(
    private val exchangeRepository: ExchangeRepository
) {

    suspend operator fun invoke(): List<ExchangeUi> {
        val resultData = exchangeRepository.getExchangeList().data

        return resultData.map { (key, exchange)->
            ExchangeUi(
                id = key,
                name = exchange.name,
                logo = exchange.logo,
                spotVolumeUsd = NumberFormat.getCurrencyInstance(Locale.US).format(exchange.spotVolumeUsd),
                dateLaunched = exchange.dateLaunched?.formatDate() ?: ""
            )
        }
    }

}