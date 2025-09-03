package com.studiomk.domain.extensions

import junit.framework.TestCase.assertEquals
import org.junit.Test


class DateExtensionsKtTest {

    @Test
    fun testFormatDate() {
        // Given
        val stringDate = "2022-11-24T08:23:22.028Z"

        // When
        val result = stringDate.formatDate()

        // Then
        assertEquals("24/11/2022", result)
    }
}