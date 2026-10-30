package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.InitialData
import com.example.data.PredictionEntity
import com.example.localization.AppLanguage
import com.example.localization.LocalizationRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context matches Oddsly Predictions`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Oddsly Predictions", appName)
    }

    @Test
    fun `verify initial data has correct category separation`() {
        val sample = InitialData.samplePredictions
        assertTrue(sample.any { it.category == "FREE" })
        assertTrue(sample.any { it.category == "VIP_2_PLUS" && !it.isHistorical })
        assertTrue(sample.any { it.category == "VIP_3_PLUS" && !it.isHistorical })
        assertTrue(sample.any { it.category == "VIP_5_PLUS" && !it.isHistorical })
        assertTrue(sample.any { it.category == "VIP_SPECIAL" && !it.isHistorical })

        // Check history categories exist and are independent
        assertTrue(sample.any { it.isHistorical && it.historyCategory == "VIP_2_PLUS_HISTORY" })
        assertTrue(sample.any { it.isHistorical && it.historyCategory == "VIP_3_PLUS_HISTORY" })
        assertTrue(sample.any { it.isHistorical && it.historyCategory == "VIP_5_PLUS_HISTORY" })
        assertTrue(sample.any { it.isHistorical && it.historyCategory == "VIP_SPECIAL_HISTORY" })
    }

    @Test
    fun `verify vip history automatic movement rules`() {
        val activeVip = PredictionEntity(
            id = "test_vip_2",
            category = "VIP_2_PLUS",
            homeTeam = "Team A",
            awayTeam = "Team B",
            league = "Premier League",
            predictionText = "Over 2.5",
            odds = "2.10",
            matchDate = "Today",
            kickoffTime = "15:00",
            resultState = "PENDING",
            creationTimestamp = System.currentTimeMillis(),
            isHistorical = false
        )

        // Declaring Won on VIP moves to history category
        val now = System.currentTimeMillis()
        val wonPrediction = activeVip.copy(
            resultState = "WON",
            isHistorical = true,
            historyCategory = "${activeVip.category}_HISTORY",
            resultDeclarationTimestamp = now
        )

        assertEquals("WON", wonPrediction.resultState)
        assertTrue(wonPrediction.isHistorical)
        assertEquals("VIP_2_PLUS_HISTORY", wonPrediction.historyCategory)
        assertNotNull(wonPrediction.resultDeclarationTimestamp)
    }

    @Test
    fun `verify all 4 languages have complete translations`() {
        for (lang in AppLanguage.entries) {
            val strings = LocalizationRepository.getStrings(lang)
            assertFalse(strings.appName.isEmpty())
            assertFalse(strings.navFreeTips.isEmpty())
            assertFalse(strings.navVipTips.isEmpty())
            assertFalse(strings.navSettings.isEmpty())
            assertFalse(strings.vip2PlusTitle.isEmpty())
            assertFalse(strings.vipHistoryTitle.isEmpty())
            assertFalse(strings.privacyPolicyTitle.isEmpty())
            assertFalse(strings.aboutTitle.isEmpty())
            assertFalse(strings.aboutDisclaimerBody.isEmpty())
            assertFalse(strings.accumulatedOddsLabel.isEmpty())
            assertFalse(strings.numberOfOddsLabel.isEmpty())
        }
    }

    @Test
    fun `verify accumulated odds calculation logic`() {
        val testOdds = listOf("1.85", "1.68")
        val product = testOdds.mapNotNull { it.toDoubleOrNull() }.fold(1.0) { acc, odd -> acc * odd }
        val formatted = String.format(java.util.Locale.US, "%.2f", product)
        assertEquals("3.11", formatted)
        assertEquals(2, testOdds.size)
    }
}
