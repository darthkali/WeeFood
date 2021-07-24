package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController


@Composable
fun BottomBar(
    navController: NavController
) {
    val items = listOf(
        NavigationItem.Settings,
        NavigationItem.RecipeList,
        NavigationItem.WeekList,
        NavigationItem.ShoppingList,
        NavigationItem.Playground
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon!!), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}



