package de.darthkali.weefood.android.presentation.screens.recipe_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.components.MyFloatingActionButton
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.NavigationItem
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.screens.recipe_list.components.RecipeList
import de.darthkali.weefood.android.presentation.screens.recipe_list.components.SearchAppBar
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.presentation.ingredient_list.IngredientListEvents
import de.darthkali.weefood.presentation.new_recipe.NewRecipeEvents
import de.darthkali.weefood.presentation.recipe_list.RecipeListEvents
import de.darthkali.weefood.presentation.recipe_list.RecipeListState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    navController: NavController,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading,
    ) {
        Scaffold(
            topBar = {
                TopBar(title = "Rezeptliste")
            },
            bottomBar = { BottomBar(navController) }
        ) { innerPadding ->
            Column(){
            MyFloatingActionButton(
                onClick = {
                    navController.popBackStack() //TODO: Why
                    navController.navigate("${NavigationItem.NewRecipe.route}/${0}")
                          },
                color = MaterialTheme.colors.primary
            )
            Box(modifier = Modifier.padding(innerPadding)) {
                Column() {
                    SearchAppBar(
                        query = state.query,
                        onQueryChanged = {
                            onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                        },
                        onExecuteSearch = {
                            onTriggerEvent(RecipeListEvents.NewSearch)
                        },
                    )

                    RecipeList(
                        loading = state.isLoading,
                        recipeDbs = state.recipeDbs,
                        page = state.page,
                        onTriggerNextPage = {
                            onTriggerEvent(RecipeListEvents.NextPage)
                        },
                        onClickRecipeListItem = onClickRecipeListItem
                    )
                }
            }
            }
        }
    }
}