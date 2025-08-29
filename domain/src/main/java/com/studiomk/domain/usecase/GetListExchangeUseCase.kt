package com.studiomk.domain.usecase

import com.studiomk.data.ExchangeRepository
import com.studiomk.domain.model.ExchangeUi

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
                spotVolumeSd = exchange.spotVolumeUsd,
                dateLaunched = exchange.dateLaunched
            )
        }
    }
}