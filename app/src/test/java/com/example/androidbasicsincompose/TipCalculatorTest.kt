package com.example.androidbasicsincompose

import android.content.Context
import androidx.compose.runtime.Composable
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat
import java.util.*

class TipCalculatorTest {

    @Test
    fun calculate_20_percent_tip_no_round() {
        val amount = 10.00
        val tipPercentage = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance(Locale("en", "in")).format(2)
        val actualTip = calculateTip(
            amount = amount,
            tipPercent = tipPercentage,
            roundUp = false
        )
        assertEquals(expectedTip, actualTip)
    }
}