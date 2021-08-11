package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import de.darthkali.weefood.android.presentation.screens.day_list.DayListScreen
import de.darthkali.weefood.android.presentation.screens.week_list.WeekListScreen
import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListScreen
import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListViewModel
import de.darthkali.weefood.android.presentation.screens.new_recipe.NewRecipeScreen
import de.darthkali.weefood.android.presentation.screens.new_recipe.NewRecipeViewModel
import de.darthkali.weefood.android.presentation.screens.new_recipe.RecipeDetailScreen
import de.darthkali.weefood.android.presentation.screens.new_recipe.RecipeDetailViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListScreen
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import de.darthkali.weefood.android.presentation.screens.settings.SettingsScreen
import de.darthkali.weefood.android.presentation.screens.shopping_list.ShoppingListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getStateViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * Navigation Class
 * Here we set the Routes for the App
 *
 * @author Danny Steinbrecher
 */
@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItem.WeekList.route) {


        /**
         * Navigation -> WeekList
         */
        composable(
            route = NavigationItem.WeekList.route
        ) {
            WeekListScreen(navController)
        }


        /**
         * Navigation -> DayList
         */
        composable(
            route = NavigationItem.DayList.route
        ) { navBackStackEntry ->
            DayListScreen(navController)
        }


        /**
         * Navigation -> RecipeList
         */
        composable(
            route = NavigationItem.RecipeList.route + "?query={query}",
            arguments = listOf(navArgument("query") {
                defaultValue = ""
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val viewModel = getViewModel<RecipeListViewModel> {
                parametersOf(backStackEntry.arguments?.getString("query"))
            }
            RecipeListScreen(
                state = viewModel.state.value,
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickRecipeListItem = { recipeId ->
                    navController.popBackStack() //TODO: Why
                    navController.navigate("${NavigationItem.RecipeDetail.route}?recipeId=$recipeId")
                }
            )
        }


        /**
         * Navigation -> RecipeDetail
         */
        composable(
            route = NavigationItem.RecipeDetail.route + "?recipeId={recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            val recipeDetailViewModel = getStateViewModel<RecipeDetailViewModel>(state = { backStackEntry.arguments!! })
            RecipeDetailScreen(
                state = recipeDetailViewModel.state.value,
                navController = navController,
                viewModel = recipeDetailViewModel
            )
        }


        /**
         * Navigation -> NewRecipe
         */
        composable(
            route = NavigationItem.NewRecipe.route + "?recipeId={recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            val newRecipeViewModel = getStateViewModel<NewRecipeViewModel>(state = { backStackEntry.arguments!! })
            NewRecipeScreen(
                state = newRecipeViewModel.state.value,
                navController = navController,
                onTriggerEvent = newRecipeViewModel::onTriggerEvent,
                viewModel = newRecipeViewModel
            )

        }


        /**
         * Navigation -> IngredientList
         */
        composable(
            route = NavigationItem.IngredientList.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val viewModel = getViewModel<IngredientListViewModel> {
                parametersOf(backStackEntry.arguments?.getInt("recipeId"))
            }
            IngredientListScreen(
                state = viewModel.state.value,
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent,
            )
        }


        /**
         * Navigation -> ShoppingList
         */
        composable(
            route = NavigationItem.ShoppingList.route
        ) {
            ShoppingListScreen(navController)
        }


        /**
         * Navigation -> Settings
         */
        composable(
            route = NavigationItem.Settings.route
        ) {
            SettingsScreen(navController)
        }

    }
}
