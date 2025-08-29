package com.studiomk.domain.usecase

import com.studiomk.data.ExchangeRepository
import com.studiomk.domain.model.ExchangeUiObject

class GetListExchangeUseCase(
    private val exchangeRepository: ExchangeRepository
) {

    suspend operator fun invoke(): List<ExchangeUiObject> {
        val resultData = exchangeRepository.getExchangeList().data

        return resultData.map { (key, exchange)->
            ExchangeUiObject(
                id = key,
                name = exchange.name,
                logo = exchange.logo,
                spotVolumeSd = exchange.spotVolumeUsd,
                dateLaunched = exchange.dateLaunched
            )
        }
    }
}