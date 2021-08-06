package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import de.darthkali.weefood.android.presentation.screens.day_list.DayListScreen
import de.darthkali.weefood.android.presentation.screens.day_list.WeekListScreen
import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListScreen
import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListViewModel
import de.darthkali.weefood.android.presentation.screens.new_recipe.NewRecipeScreen
import de.darthkali.weefood.android.presentation.screens.new_recipe.NewRecipeViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListScreen
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import de.darthkali.weefood.android.presentation.screens.settings.SettingsScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getViewModel

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
//            val viewModel = getViewModel<NewRecipeViewModel>()
//            NewRecipeScreen(
//                state = viewModel.state.value,
//                navController = navController,
//                onTriggerEvent = viewModel::onTriggerEvent
//            )

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
            route = NavigationItem.RecipeList.route
        ) {
            val viewModel = getViewModel<RecipeListViewModel>()
            RecipeListScreen(
                state = viewModel.state.value,
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickRecipeListItem = { recipeId ->
                    navController.popBackStack() //TODO: Why
                    navController.navigate("${NavigationItem.NewRecipe.route}/$recipeId")
                }
            )
        }


//        /**
//         * Navigation -> RecipeDetail
//         */
//        composable(
//            route = NavigationItem.RecipeDetail.route + "/{recipeId}",
//            arguments = listOf(navArgument("recipeId") {
//                type = NavType.IntType
//            })
//        ) { navBackStackEntry ->
//              NewRecipeScreen(navController)
//        }


        /**
         * Navigation -> NewRecipe
         */
        composable(
            route = NavigationItem.NewRecipe.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { backStackEntry  ->
            val viewModel = getViewModel<NewRecipeViewModel>()
            viewModel.state.value.recipe.internalId = backStackEntry.arguments?.getInt("recipeId")
            NewRecipeScreen(
                state = viewModel.state.value,
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent
            )
        }


        /**
         * Navigation -> IngredientList
         */
        composable(
            route = NavigationItem.IngredientList.route
        ) {
            val viewModel = getViewModel<IngredientListViewModel>()
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
            val viewModel = getViewModel<IngredientListViewModel>()
            IngredientListScreen(
                state = viewModel.state.value,
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent,
            )
        }


        /**
         * Navigation -> Settings
         */
        composable(
            route = NavigationItem.Settings.route
        ) { navBackStackEntry ->
            SettingsScreen(navController)
        }

    }
}