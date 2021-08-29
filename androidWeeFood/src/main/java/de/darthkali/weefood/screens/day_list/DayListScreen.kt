package de.darthkali.weefood.screens.day_list

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
fun DayListScreen(
    navController: NavController
) {
    AppTheme() {
        Scaffold(
            topBar = { TopBar(title = "Mittwoch") },
            bottomBar = { BottomBar(navController) }
        ) {
            Text(text = "DayListScreen")
        }
    }

}
