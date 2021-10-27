package de.darthkali.weefood

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalStdlibApi

@RunWith(AndroidJUnit4::class)
class WeekListScreenTest : BaseUiTest() {

    @Before
    fun setUp() {
        composeTestRule.launchWeeFoodApp()
    }

    /**
     * Checks that the app is launching on the WeekListScreen
     */
    @Test
    fun app_launches_on_week_list_screen() {
        composeTestRule.onNodeWithText("WeeFood").assertExists()
    }

    /**
     * Checks that the RecipeListScreen will be opened when clicking on the BottomBar
     */
    @Test
    fun goes_to_recipe_screen() {
        composeTestRule.onNodeWithText("WeekList").assertExists()
        composeTestRule.onNodeWithText("Rezepte").performClick()
        composeTestRule.onNodeWithText("Rezeptliste").assertExists()
    }

    /**
     * Checks that the ShoppingListScreen will be opened when clicking on the BottomBar
     */
    @Test
    fun goes_to_shopping_list_screen() {
        composeTestRule.onNodeWithText("WeekList").assertExists()
        composeTestRule.onNodeWithText("Einkaufsliste").performClick()
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onAllNodesWithText("Einkaufsliste")
            .assertCountEquals(2)
            .onFirst()
            .assertExists()
    }
}
