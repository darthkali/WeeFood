package de.darthkali.weefood.android.presentation.screens.recipe_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
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
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.navigation.NavigationItem
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun NewRecipeScreen(
    viewModel: RecipeDetailViewModel,
    navController: NavController,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
    onClickAddIngredient: (Int?) -> Unit,
    onClickSaveRecipeDetailFAB: (Int?) -> Unit,
    onClickEditRecipeDetailFAB: (Int?) -> Unit,
    onClickBackInEditableRecipeDetailScreen: (Int?) -> Unit,
    onClickBackInViewableRecipeDetailScreen: (String) -> Unit,
) {

    AppTheme {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            snackbarHost = {
                // reuse default SnackbarHost to have default animation and timing handling
                SnackbarHost(it) { data ->
                    // custom snackbar with the custom border
                    Snackbar(
                        snackbarData = data
                    )
                }
            },
            topBar = {
                if (viewModel.editable.value) {
                    EditableRecipeDetailScreenTopBar(
                        recipe = viewModel.state.value.recipe,
                        navController = navController,
                        onClickBackInEditableRecipeDetailScreen = onClickBackInEditableRecipeDetailScreen
                    )
                } else {
                    ViewableRecipeDetailScreenTopBar(
                        recipe = viewModel.state.value.recipe,
                        onClickBackInViewableRecipeDetailScreen = onClickBackInViewableRecipeDetailScreen
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                if (viewModel.editable.value) {
                    SaveRecipeDetailFAB(
                        recipe = viewModel.state.value.recipe,
                        onClickSaveRecipeDetailFAB = onClickSaveRecipeDetailFAB,
                        onTriggerEvent = onTriggerEvent,
                        scope = scope,
                        scaffoldState = scaffoldState
                    )

                } else {
                    EditRecipeDetailFAB(
                        recipe = viewModel.state.value.recipe,
                        onTriggerEvent = onTriggerEvent,
                        onClickEditRecipeDetailFAB = onClickEditRecipeDetailFAB
                    )
                }
            }

        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {

                if (viewModel.editable.value) {
                    EditableRecipeDetail(
                        recipe = viewModel.state.value.recipe,
                        onTriggerEvent = onTriggerEvent,
                        onClickAddIngredient = onClickAddIngredient,
                    )
                } else {
                    ViewableRecipeDetail(
                        recipe = viewModel.state.value.recipe,
                    )
                }


            }
        }
    }
}

@Composable
fun SaveRecipeDetailFABALT(
    recipe: Recipe,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
    onClickSaveRecipeDetailFAB: (Int?) -> Unit,
) {

    val snackbarVisibleState = remember { mutableStateOf(false) }
    FloatingActionButton(

        onClick = {

            if (recipe.name != "") {
                onTriggerEvent(RecipeDetailEvents.OnSaveRecipe)
                onClickSaveRecipeDetailFAB(recipe.databaseId)
                snackbarVisibleState.value = false
            } else {
                snackbarVisibleState.value = true
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(6.dp)
    ) {
        Icon(Icons.Filled.Check, "")
    }
    if (snackbarVisibleState.value) {
        Snackbar(
            modifier = Modifier.padding(8.dp)
        ) { Text(text = "This is a snackbar!") }
    }
}

@Composable
fun SaveRecipeDetailFAB(
    recipe: Recipe,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
    onClickSaveRecipeDetailFAB: (Int?) -> Unit,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    FloatingActionButton(
        onClick = {
            if (recipe.name != "") {
                onTriggerEvent(RecipeDetailEvents.OnSaveRecipe)
                onClickSaveRecipeDetailFAB(recipe.databaseId)

            } else {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Bitte einen Rezeptnamen eingeben!")
                }
            }
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
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
    onClickEditRecipeDetailFAB: (Int?) -> Unit,
) {
    FloatingActionButton(
        onClick = {
            onTriggerEvent(RecipeDetailEvents.OnSaveRecipe)
            onClickEditRecipeDetailFAB(recipe.databaseId)
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
    onClickBackInEditableRecipeDetailScreen: (Int?) -> Unit,
) {

    TopBar(
        title = "Neues Rezept",
        navigationIcon = Icons.Filled.ArrowBack,
        navigationIconClickAction = {

            recipe.databaseId?.let {
                onClickBackInEditableRecipeDetailScreen(recipe.databaseId)
            } ?: run {
                navController.navigate(
                    NavigationItem.Settings.route // TODO: Wenn wir ein neues REzept erstellen und es noch keine ID hat
                )
            }
        }
    )
}

@Composable
fun ViewableRecipeDetailScreenTopBar(
    recipe: Recipe,
    onClickBackInViewableRecipeDetailScreen: (String) -> Unit,
) {
    TopBar(
        title = recipe.name,
        navigationIcon = Icons.Filled.ArrowBack,
        navigationIconClickAction = {
            onClickBackInViewableRecipeDetailScreen(recipe.name)
        }
    )
}



