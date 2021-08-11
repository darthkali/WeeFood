package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.darthkali.weefood.android.presentation.components.button.CommonButton
import de.darthkali.weefood.android.presentation.components.button.MyFloatingActionButton
import de.darthkali.weefood.android.presentation.components.button.ButtonStyle
import de.darthkali.weefood.android.presentation.navigation.BottomBar
import de.darthkali.weefood.android.presentation.navigation.NavigationItem
import de.darthkali.weefood.android.presentation.navigation.TopBar
import de.darthkali.weefood.android.presentation.screens.new_recipe.components.IngredientCard
import de.darthkali.weefood.android.presentation.screens.new_recipe.components.IngredientUnitTextField
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.presentation.new_recipe.NewRecipeEvents
import de.darthkali.weefood.presentation.new_recipe.NewRecipeState
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailEvents
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    navController: NavController,
    viewModel: RecipeDetailViewModel
) {

    AppTheme() {
        Scaffold(
            topBar = {
                TopBar(
                    title = state.recipe.name,
                    navigationIcon = Icons.Filled.ArrowBack,
                    navigationIconClickAction = { navController.navigateUp() },
                    navController = navController
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(
                            "${NavigationItem.NewRecipe.route}?recipeId=${state.recipe.databaseId}"
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary,
//                        contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp)
                ) {
                    Icon(Icons.Filled.Create, "")
                }
            }
        ) { innerPadding ->


            Box(modifier = Modifier.padding(innerPadding)) {

                LazyColumn() {
                    item {
                        Text("Bild")
                        Row() {
                            Text("Zuatten pro Portion")
                            Text("Plus Button")
                        }
                    }

                    itemsIndexed(
                        items = state.recipe.ingredients
                    ) { _, ingredients ->

                        Row() {
                            Text(ingredients.name ?: "")
                            Text(ingredients.quantity.toString())
                            Text(ingredients.unit)
                        }
                    }



                    item {
                        Text("Kochzeit")
                        Row() {
                            Text(state.recipe.cooking_time.toString())
                            Text(state.recipe.cooking_time_unit)
                        }

                        Text("Rezept")
                        Text(state.recipe.recipeDescription ?: "")
                    }
                }
            }
        }
    }
}



//@ExperimentalFoundationApi
//@ExperimentalCoroutinesApi
//@ExperimentalMaterialApi
//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun NewRecipeScreenPreview() {
//
//    val navController = rememberNavController()
//    AppTheme() {
//        NewRecipeScreen(navController)
//    }
//}