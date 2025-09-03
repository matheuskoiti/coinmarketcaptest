package com.studiomk.domain.usecase

import com.studiomk.data.RequestResult
import com.studiomk.data.repository.ExchangeRepository
import com.studiomk.domain.TestFixtures
import com.studiomk.domain.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.studiomk.data.model.ApiResponse as ApiResponse1


class GetListExchangeUseCaseTest {

    private lateinit var getListExchangeUseCase: GetListExchangeUseCase
    private lateinit var exchangeRepository: ExchangeRepository

    @Before
    fun setUp() {
        exchangeRepository = mockk()
        getListExchangeUseCase = GetListExchangeUseCase(exchangeRepository)
    }

    @Test
    fun `invoke should return a list of exchange uis`(): Unit = runBlocking {
        // Given
        val mockResponse = ApiResponse1(
            status = TestFixtures.mockStatus,
            data = mapOf("100" to TestFixtures.mockExchange)
        )
        coEvery { exchangeRepository.getExchangeList(any()) } returns RequestResult.Success(mockResponse)

        // When
        val result = getListExchangeUseCase.invoke()

        // Then
        assert(result is Result.Success)
        with((result as Result.Success).data.first()){
            assertEquals(name, TestFixtures.mockExchange.name)
            assertEquals(logo, TestFixtures.mockExchange.logo)
        }
    }

    @Test
    fun `invoke should return an error result when there is a network error`(): Unit = runBlocking {
        // Given
        coEvery { exchangeRepository.getExchangeList(any()) } returns RequestResult.Error("error message")

        // When
        val result = getListExchangeUseCase.invoke()

        // Then
        assert(result is Result.Error)
        assertEquals((result as Result.Error).error, "Network error: error message")
    }

}