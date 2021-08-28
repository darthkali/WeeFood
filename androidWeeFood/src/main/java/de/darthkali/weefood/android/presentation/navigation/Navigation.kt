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
import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListScreen
import de.darthkali.weefood.android.presentation.screens.ingredient_list.IngredientListViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_detail.NewRecipeScreen
import de.darthkali.weefood.android.presentation.screens.recipe_detail.RecipeDetailViewModel
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListScreen
import de.darthkali.weefood.android.presentation.screens.recipe_list.RecipeListViewModel
import de.darthkali.weefood.android.presentation.screens.settings.SettingsScreen
import de.darthkali.weefood.android.presentation.screens.shopping_list.ShoppingListScreen
import de.darthkali.weefood.android.presentation.screens.week_list.WeekListScreen
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
                viewModel = viewModel,
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickOpenRecipe = { recipeId ->
                    navController.navigate("${NavigationItem.RecipeDetail.route}?recipeId=$recipeId")
                },
                onClickAddNewRecipe = {
                    navController.navigate("${NavigationItem.RecipeDetail.route}?editable=true")
                }
            )
        }

        /**
         * Navigation -> RecipeDetail
         */
        composable(
            route = NavigationItem.RecipeDetail.route + "?recipeId={recipeId}&editable={editable}",
            arguments = listOf(
                navArgument("recipeId") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                },
                navArgument("editable") {
                    defaultValue = false
                    type = NavType.BoolType
                },
            )
        ) { backStackEntry ->
            val viewModel =
                getStateViewModel<RecipeDetailViewModel>(state = { backStackEntry.arguments!! })
            NewRecipeScreen(
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent,
                viewModel = viewModel,
                onClickSaveRecipeDetailFAB = { recipeId ->
                    navController.navigate(
                        "${NavigationItem.RecipeDetail.route}?recipeId=${recipeId}"
                    )
                },
                onClickEditRecipeDetailFAB = { recipeId ->
                    navController.navigate(
                        "${NavigationItem.RecipeDetail.route}?recipeId=${recipeId}&editable=true"
                    )
                },
                onClickBackInEditableRecipeDetailScreen = { recipeId ->
                    try {
                        if (recipeId != null && recipeId > 0) {
                            navController.navigate(
                                "${NavigationItem.RecipeDetail.route}?recipeId=${recipeId}"
                            )
                        } else {
                            navController.navigate(
                                "${NavigationItem.RecipeList.route}"
                            )
                        }
                    } catch (e: Exception) {
                        //log.error("Error")
                    } finally {
                        navController.navigate(
                            "${NavigationItem.RecipeList.route}"
                        )
                    }
                },
                onClickBackInViewableRecipeDetailScreen = { recipeName ->
                    navController.navigate(
                        "${NavigationItem.RecipeList.route}?query=${recipeName}"
                    )
                },
                onClickAddIngredient = { recipeId ->
                    navController.navigate(
                        "${NavigationItem.IngredientList.route}/${recipeId}"
                    )
                }
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
                viewModel = viewModel,
                navController = navController,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickBack = { recipeId ->
                    navController.navigate(
                        "${NavigationItem.RecipeDetail.route}?recipeId=${recipeId}&editable=true"
                    )
                },
                onClickSaveIngredient = { recipeId ->
                    navController.navigate(
                        "${NavigationItem.RecipeDetail.route}?recipeId=${recipeId}&editable=true"
                    )
                }
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

