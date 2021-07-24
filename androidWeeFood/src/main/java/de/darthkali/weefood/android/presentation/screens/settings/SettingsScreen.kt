package de.darthkali.weefood.android.presentation.screens.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun SettingsScreen(
    navController: NavController
) {
    AppTheme() {
        Scaffold(
            topBar = { TopBar(title = "Einkaufsliste") },
            bottomBar = { BottomBar(navController) }
        ) {
            Text(text = "SettingsScreen")
        }
    }

}
