package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.navigation.NavigationItem
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.screens.recipe_detail.ViewableRecipeDetail
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailEvents
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun NewRecipeScreen(
    navController: NavController,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
    detailViewModel: RecipeDetailViewModel
) {

    AppTheme() {
        Scaffold(
            topBar = {
                if (detailViewModel.editable.value) {
                    EditableRecipeDetailScreenTopBar(
                        recipe = detailViewModel.state.value.recipe,
                        navController = navController,
                    )
                } else {
                    ViewableRecipeDetailScreenTopBar(
                        recipe = detailViewModel.state.value.recipe,
                        navController = navController,
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                if (detailViewModel.editable.value) {
                    SaveRecipeDetailFAB(
                        recipe = detailViewModel.state.value.recipe,
                        navController = navController,
                        onTriggerEvent = onTriggerEvent
                    )

                } else {
                    EditRecipeDetailFAB(
                        recipe = detailViewModel.state.value.recipe,
                        navController = navController,
                        onTriggerEvent = onTriggerEvent
                    )
                }
            }

        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {

                if (detailViewModel.editable.value) {
                    EditableRecipeDetail(
                        recipe = detailViewModel.state.value.recipe,
                        onTriggerEvent = onTriggerEvent,
                        navController = navController
                    )
                } else {
                    ViewableRecipeDetail(
                        recipe = detailViewModel.state.value.recipe,
                    )
                }


            }
        }
    }
}

@Composable
fun SaveRecipeDetailFAB(
    recipe: Recipe,
    navController: NavController,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
) {
    FloatingActionButton(
        onClick = {
            onTriggerEvent(RecipeDetailEvents.OnSaveRecipe)
            navController.navigate(
                "${NavigationItem.RecipeDetail.route}?recipeId=${recipe.databaseId}"
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(6.dp)
    ) {
        Icon(Icons.Filled.Check, "")
    }
}

@Composable
fun EditRecipeDetailFAB(
    recipe: Recipe,
    navController: NavController,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
) {
    FloatingActionButton(
        onClick = {
            onTriggerEvent(RecipeDetailEvents.OnSaveRecipe)
            navController.navigate(
                "${NavigationItem.RecipeDetail.route}?recipeId=${recipe.databaseId}&editable=true"
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(6.dp)
    ) {
        Icon(Icons.Filled.Create, "")
    }
}


@Composable
fun EditableRecipeDetailScreenTopBar(
    recipe: Recipe,
    navController: NavController,
) {
    TopBar(
        title = "Neues Rezept",
        navigationIcon = Icons.Filled.ArrowBack,
        navigationIconClickAction = {

            recipe.databaseId?.let {
                navController.navigate(
                    "${NavigationItem.RecipeDetail.route}?recipeId=${recipe.databaseId}"
                )
            }.run {
                navController.navigate(
                    NavigationItem.RecipeList.route
                )
            }
        }
    )
}

@Composable
fun ViewableRecipeDetailScreenTopBar(
    recipe: Recipe,
    navController: NavController,
) {
    TopBar(
        title = recipe.name,
        navigationIcon = Icons.Filled.ArrowBack,
        navigationIconClickAction = {
            navController.navigate(
                "${NavigationItem.RecipeList.route}?query=${recipe.name}"
            )
        }
    )
}



