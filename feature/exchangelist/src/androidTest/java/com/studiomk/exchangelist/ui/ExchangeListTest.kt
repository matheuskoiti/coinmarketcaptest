package com.studiomk.exchangelist.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.domain.usecase.GetListExchangeUseCase
import com.studiomk.exchangelist.ui.testutils.FakeExchangeListViewModel
import com.studiomk.exchangelist.ui.testutils.MainDispatcherRule
import com.studiomk.exchangelist.viewmodel.ExchangeState
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExchangeListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val mockExchangeUi = ExchangeUi(
        id = "1",
        name = "Binance",
        logo = "https://s2.coinmarketcap.com/static/img/exchanges/64x64/270.png",
        spotVolumeUsd = "1,456,789,012.45",
        dateLaunched = "2017-07-14",
        description = "A global cryptocurrency exchange providing a platform for trading various cryptocurrencies.",
        url = "https://www.binance.com/",
        makerFee = "0.1%",
        takerFee = "0.1%"
    )

    @Test
    fun testExchangeList() {
        composeTestRule.setContent {
            ExchangeList(
                uiState = ExchangeState.ExchangeLoadedState(listOf(mockExchangeUi)),
                modifier = Modifier,
                navController = rememberNavController()
            )
        }

        composeTestRule
            .onNodeWithText("Binance")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Date launched: 2017-07-14")
            .assertIsDisplayed()
    }

}