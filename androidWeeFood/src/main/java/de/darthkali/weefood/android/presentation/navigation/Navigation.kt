package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavType
import androidx.navigation.compose.*
import de.darthkali.weefood.android.presentation.recipe_detail.RecipeDetailScreen
import de.darthkali.weefood.android.presentation.recipe_detail.RecipeDetailViewModel
import de.darthkali.weefood.android.presentation.recipe_list.RecipeListScreen
import de.darthkali.weefood.android.presentation.recipe_list.RecipeListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalStdlibApi
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
            RecipeListScreen(
                state = viewModel.state.value,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickRecipeListItem = { recipeId ->
                    navController.navigate("${Screen.RecipeDetail.route}/$recipeId")
                }
            )
        }
        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
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
    }
}