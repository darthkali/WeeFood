package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavType
import androidx.navigation.compose.*
import de.darthkali.weefood.android.presentation.screens.day_list.DayListScreen
import de.darthkali.weefood.android.presentation.screens.day_list.WeekListScreen
import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListScreen
import de.darthkali.weefood.android.presentation.screens.new_recipe.NewRecipeScreen
import de.darthkali.weefood.android.presentation.screens.recipe_detail.RecipeDetailScreen
import de.darthkali.weefood.android.presentation.screens.recipe_detail.RecipeDetailViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListScreen
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import de.darthkali.weefood.android.presentation.screens.settings.SettingsScreen
import de.darthkali.weefood.android.presentation.screens.shopping_list.ShoppingListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItem.WeekList.route) {


        composable(
            route = NavigationItem.WeekList.route
        ) { navBackStackEntry ->
            WeekListScreen(navController)
        }


        composable(
            route = NavigationItem.DayList.route
        ) { navBackStackEntry ->
            DayListScreen(navController)
        }



        composable(
            route = NavigationItem.RecipeList.route
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
            RecipeListScreen(
                state = viewModel.state.value,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickRecipeListItem = { recipeId ->
                    navController.navigate("${NavigationItem.RecipeDetail.route}/$recipeId")
                }
            )
        }


        composable(
            route = NavigationItem.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel", factory)
            RecipeDetailScreen(
                state = viewModel.state.value,
                onTriggerEvent = viewModel::onTriggerEvent
            )
        }


        composable(
            route = NavigationItem.NewRecipe.route
        ) { navBackStackEntry ->
            NewRecipeScreen(navController)
        }

        composable(
            route = NavigationItem.IngredientList.route
        ) { navBackStackEntry ->
            IngredientListScreen(navController)
        }

        composable(
            route = NavigationItem.ShoppingList.route
        ) { navBackStackEntry ->
            ShoppingListScreen(navController)
        }

        composable(
            route = NavigationItem.Settings.route
        ) { navBackStackEntry ->
            SettingsScreen(navController)
        }
    }
}