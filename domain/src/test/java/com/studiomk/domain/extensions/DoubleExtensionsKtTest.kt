package com.studiomk.domain.extensions

import junit.framework.TestCase.assertEquals
import org.junit.Test


class DoubleExtensionsKtTest {

    @Test
    fun mapToCurrencyTest() {
        // Given
        val doubleValue = 1.45678901245E9

        // When
        val result = doubleValue.mapToCurrency()

        // Then
        assertEquals("\$1,456,789,012.45", result)
    }

}