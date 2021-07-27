package de.darthkali.weefood.android.presentation.screens.day_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.NavigationItem
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun WeekListScreen(
    navController: NavController
) {
    AppTheme() {
        Scaffold(
            topBar = { TopBar(
                title = "WeeFood",
                actionIcon = Icons.Filled.Settings,
                actionIconIconClickAction = { navController.navigate(NavigationItem.Settings.route) }
            ) },
            bottomBar = { BottomBar(navController) }
        ) {
            Text(text = "WeekList")
        }
    }

}

