package de.darthkali.weefood.android.presentation.screens.recipe_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.screens.recipe_list.components.RecipeList
import de.darthkali.weefood.android.presentation.screens.recipe_list.components.SearchAppBar
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.presentation.ingredient_list.IngredientListEvents
import de.darthkali.weefood.presentation.ingredient_list.IngredientListState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeListScreen(
    state: IngredientListState,
    navController: NavController,
    onTriggerEvent: (IngredientListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading,
    ) {
        Scaffold(
            topBar = {
                TopBar(title = "Einkaufsliste")
            },
            bottomBar = { BottomBar(navController) }
        ) {
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

                RecipeList(
                    loading = state.isLoading,
                    ingredientDbs = state.ingredients,
                    page = state.page,
                    onTriggerNextPage = {
                        onTriggerEvent(IngredientListEvents.NextPage)
                    },
                    onClickRecipeListItem = onClickRecipeListItem
                )
            }
        }
    }

}