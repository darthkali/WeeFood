package de.darthkali.weefood.android.presentation.screens.ingredient_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.NavigationItem
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.screens.ingredient_list.components.IngredientList
import de.darthkali.weefood.android.presentation.screens.ingredient_list.components.SearchAppBar
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.presentation.ingredient_list.IngredientListEvents
import de.darthkali.weefood.presentation.ingredient_list.IngredientListState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun IngredientListScreen(
    state: IngredientListState,
    navController: NavController,
    onTriggerEvent: (IngredientListEvents) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading,
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Zutaten Suche",
                    navigationIcon = Icons.Filled.ArrowBack,
                    navigationIconClickAction = { navController.navigate(NavigationItem.NewRecipe.route + "/${state.recipeId}") },
                )
            },
            bottomBar = { BottomBar(navController) }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {


                Column() {
                    SearchAppBar(
                        query = state.query,
                        onQueryChanged = {
                            onTriggerEvent(IngredientListEvents.OnUpdateQuery(it))
                        },
                        onExecuteSearch = {
                            onTriggerEvent(IngredientListEvents.NewSearch)
                        },
                    )

                    IngredientList(
                        loading = state.isLoading,
                        ingredients = state.ingredients,
                        page = state.page,
                        onTriggerNextPage = {
                            onTriggerEvent(IngredientListEvents.NextPage)
                        },
                        onSaveIngredient = {
                            onTriggerEvent(IngredientListEvents.SaveIngredient(it))
                            navController.popBackStack() //TODO: Why
                            navController.navigate("${NavigationItem.NewRecipe.route}/${state.recipeId}")
                        }

                    )
                }
            }
        }
    }
}
