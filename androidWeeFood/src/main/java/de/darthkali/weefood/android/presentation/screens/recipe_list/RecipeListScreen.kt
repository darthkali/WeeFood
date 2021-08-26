package de.darthkali.weefood.android.presentation.screens.recipe_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.screens.recipe_list.components.RecipeList
import de.darthkali.weefood.android.presentation.screens.recipe_list.components.SearchAppBar
import de.darthkali.weefood.android.presentation.theme.AppTheme
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
//    viewModel: RecipeListViewModel,
    navController: NavController,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickOpenRecipe: (Int) -> Unit,
    onClickAddNewRecipe: () -> Unit
) {
    AppTheme(
        displayProgressBar = state.isLoading,
    ) {
        Scaffold(
            topBar = {
                TopBar(title = "Rezeptliste")
            },
            bottomBar = { BottomBar(navController) },

            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onClickAddNewRecipe() },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Icon(Icons.Filled.Add, "")
                }
            }


        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column {
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
                        recipes = state.recipes,
                        page = state.page,
                        onTriggerNextPage = {
                            onTriggerEvent(RecipeListEvents.NextPage)
                        },
                        onClickOpenRecipe = onClickOpenRecipe
                    )
                }
            }
        }

    }
}