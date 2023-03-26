package com.example.androidbasicsincompose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.androidbasicsincompose.ui.theme.AndroidBasicsInComposeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            AndroidBasicsInComposeTheme {
                TipTimeScreen()
            }
        }
        composeTestRule
            .onNodeWithText("Bill Amount")
            .performTextInput("10")

        composeTestRule
            .onNodeWithText("Tip (%)")
            .performTextInput("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found.")
    }
}