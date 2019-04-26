package org.redciudadana.candidatos

import org.junit.Assert.assertEquals
import org.junit.Test
import org.redciudadana.candidatos.data.models.HistoryEntry
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.screens.diputado.DiputadoPresenter

class DiputadoFragmentTest {
    @Test
    fun phoneRegexTest() {
        var phoneNumber: String? = "22323344"
        val presenter = DiputadoPresenter()
        var result = presenter.getPhoneNumberUrl(phoneNumber)
        assertEquals("tel:22323344", result)

        phoneNumber = "12345678 / 12048238"
        result = presenter.getPhoneNumberUrl(phoneNumber)
        assertEquals("tel:12345678", result)

        phoneNumber = ""
        result = presenter.getPhoneNumberUrl(phoneNumber)
        assertEquals(null, result)

        phoneNumber = null
        result = presenter.getPhoneNumberUrl(phoneNumber)
        assertEquals(null, result)
    }

    @Test
    fun filterHistoryTest() {
        val historyList = listOf(
            HistoryEntry("1", "1992", "Viva"),
            HistoryEntry("1", "1996", "Viva"),
            HistoryEntry("3", "1995", "Viva"),
            HistoryEntry("1", "1991", "Viva"),
            HistoryEntry("3", "1999", "Viva"),
            HistoryEntry("1", "1997", "Viva")
        )
        val profile = Profile(id = "1")
        val presenter = DiputadoPresenter()
        val result = presenter.filterHistory(historyList, profile)
        val expected = listOf(
            HistoryEntry("1", "1991", "Viva"),
            HistoryEntry("1", "1992", "Viva"),
            HistoryEntry("1", "1996", "Viva"),
            HistoryEntry("1", "1997", "Viva")
        )
        assertEquals(expected, result)

    }
}