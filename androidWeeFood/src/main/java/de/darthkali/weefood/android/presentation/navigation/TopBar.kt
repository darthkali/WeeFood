package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun TopBar(
    title: String = "",
    navigationIcon: ImageVector? = null,
    navigationIconClickAction: (() -> Unit)? = null,
    actionIcon: ImageVector? = null,
    actionIconIconClickAction: (() -> Unit)? = null,
    navController: NavController?
) {
    TopAppBar(
        navigationIcon = setIcon(navigationIcon, navController = navController),
        title = { Text(title) },
        actions = {
            actionIconIconClickAction?.let { it ->
                IconButton(onClick = it) {

                    actionIcon?.let { Icon(it, "") }
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
    )
}

//@Composable
fun setIcon(
    icon: ImageVector? = null,
    iconClickAction: (() -> Unit)? = null,
    navController: NavController?
): @Composable() (() -> Unit)? {
    icon?.let {
        return {
            Icon(
                imageVector = it,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { navController?.navigateUp() })
                    .fillMaxHeight()
            )
        }
    }
    return null
}