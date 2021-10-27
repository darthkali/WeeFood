package de.darthkali.weefood.screens.shopping_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import de.darthkali.weefood.navigation.BottomBar
import de.darthkali.weefood.navigation.TopBar
import de.darthkali.weefood.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ShoppingListScreen(
    navController: NavController
) {
    AppTheme() {
        Scaffold(
            topBar = { TopBar(title = "Einkaufsliste") },
            bottomBar = { BottomBar(navController) }
        ) {
            Text(text = "ShoppingListScreen")
        }
    }
}
