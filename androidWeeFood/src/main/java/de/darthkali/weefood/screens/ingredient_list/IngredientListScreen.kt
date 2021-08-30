package de.darthkali.weefood.screens.ingredient_list

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
import de.darthkali.weefood.navigation.TopBar
import de.darthkali.weefood.screens.ingredient_list.components.IngredientList
import de.darthkali.weefood.screens.ingredient_list.components.SearchAppBar
import de.darthkali.weefood.theme.AppTheme
import de.darthkali.weefood.presentation.ingredient_list.IngredientListEvents
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun IngredientListScreen(
    viewModel: IngredientListViewModel,
    navController: NavController,
    onTriggerEvent: (IngredientListEvents) -> Unit,
    onClickSaveIngredient: (Int?) -> Unit,
    onClickBack: (Int?) -> Unit,
) {
    AppTheme(
        displayProgressBar = viewModel.state.value.isLoading,
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Zutaten Suche",
                    navigationIcon = Icons.Filled.ArrowBack,
                    navigationIconClickAction = {
                        onClickBack(viewModel.state.value.recipeId)
                    },
                )
            },
//            bottomBar = { BottomBar(navController) }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {


                Column {
                    SearchAppBar(
                        query = viewModel.state.value.query,
                        onQueryChanged = {
                            onTriggerEvent(IngredientListEvents.OnUpdateQuery(it))
                        },
                        onExecuteSearch = {
                            onTriggerEvent(IngredientListEvents.NewSearch)
                        },
                    )

                    IngredientList(
                        loading = viewModel.state.value.isLoading,
                        ingredients = viewModel.state.value.ingredients,
                        page = viewModel.state.value.page,
                        onTriggerNextPage = {
                            onTriggerEvent(IngredientListEvents.NextPage)
                        },
                        onSaveIngredient = {
                            onTriggerEvent(IngredientListEvents.SaveIngredient(it))
                            onClickSaveIngredient(viewModel.state.value.recipeId)
                        }
                    )
                }
            }
        }
    }
}
