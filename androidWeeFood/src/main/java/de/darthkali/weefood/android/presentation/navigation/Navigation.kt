package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
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
import de.darthkali.weefood.android.presentation.screens.recipe_detail.RecipeDetailScreen
import de.darthkali.weefood.android.presentation.screens.recipe_detail.RecipeDetailViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListScreen
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import de.darthkali.weefood.android.presentation.screens.settings.SettingsScreen
import de.darthkali.weefood.android.presentation.screens.shopping_list.ShoppingListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getViewModel
import androidx.lifecycle.viewmodel.compose.viewModel as viewModel

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
fun Navigation(){



    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItem.WeekList.route) {


        /**
         * Navigation -> WeekList
         */
        composable(
            route = NavigationItem.WeekList.route
        ) { navBackStackEntry ->
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
        ) { navBackStackEntry ->
//            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
            val viewModel = getViewModel<RecipeListViewModel>()
            RecipeListScreen(
                state = viewModel.state.value,
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickRecipeListItem = { recipeId ->
                    navController.navigate("${NavigationItem.RecipeDetail.route}/$recipeId")
                }
            )
        }


        /**
         * Navigation -> RecipeDetail
         */
        composable(
            route = NavigationItem.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
//            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//            val viewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel", factory)
            val viewModel = getViewModel<RecipeDetailViewModel>()
            RecipeDetailScreen(
                state = viewModel.state.value,
                //onTriggerEvent = viewModel::onTriggerEvent
            )
        }


        /**
         * Navigation -> NewRecipe
         */
        composable(
            route = NavigationItem.NewRecipe.route
        ) { navBackStackEntry ->
            NewRecipeScreen(navController)
        }


        /**
         * Navigation -> IngredientList
         */
        composable(
            route = NavigationItem.IngredientList.route
        ) { navBackStackEntry ->
//            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//            val viewModel: IngredientListViewModel = viewModel("RecipeListViewModel", factory)
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
        ) { navBackStackEntry ->
//            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//            val viewModel: IngredientListViewModel = viewModel("RecipeListViewModel", factory)
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