package com.studiomk.exchangelist.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.studiomk.domain.usecase.GetListExchangeUseCase
import com.studiomk.exchangelist.ui.testutils.FakeExchangeListViewModel
import com.studiomk.exchangelist.ui.testutils.MainDispatcherRule
import com.studiomk.exchangelist.viewmodel.ExchangeState
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExchangeListScreenKtTest {

    private lateinit var getListExchangeUseCase: GetListExchangeUseCase

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        getListExchangeUseCase = mockk(relaxed = true)
    }

    @Test
    fun testLoadingExchangeListScreen() {
        // Given
        val fakeExchangeListViewModel = FakeExchangeListViewModel(
            getListExchangeUseCase = getListExchangeUseCase,
            dispatcher = mainDispatcherRule.testDispatcher
        )
        composeTestRule.setContent {
            ExchangeListScreen(viewModel = fakeExchangeListViewModel)
        }

        // When
        fakeExchangeListViewModel.emitState(ExchangeState.ExchangeLoading)

        // Then
        composeTestRule
            .onNodeWithText("Loading exchange list!!")
            .assertIsDisplayed()
    }

}