package `in`.tweets.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Rule
import org.junit.Test


class CategoryScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun testLoadingState() {
        composeTestRule.setContent {
            CategoryScreen(onClick = {})
        }
        composeTestRule.onNodeWithText("Loading...").assertIsDisplayed()
    }
}