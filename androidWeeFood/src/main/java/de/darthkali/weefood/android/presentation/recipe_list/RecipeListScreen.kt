package de.darthkali.weefood.android.presentation.recipe_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import de.darthkali.weefood.android.presentation.recipe_list.components.RecipeList
import de.darthkali.weefood.android.presentation.recipe_list.components.SearchAppBar
import de.darthkali.weefood.android.presentation.theme.AppTheme
import de.darthkali.weefood.presentation.recipe_list.RecipeListEvents
import de.darthkali.weefood.presentation.recipe_list.FoodCategoryUtil
import de.darthkali.weefood.presentation.recipe_list.RecipeListState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
            onTriggerEvent(RecipeListEvents.OnRemoveHeadMessageFromQueue)
        }
    ) {
        val foodCategories = remember{FoodCategoryUtil().getAllFoodCategories()}
        Scaffold(
            topBar = {
                SearchAppBar(
                    query = state.query,
                    onQueryChanged = {
                        onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                    },
                    onExecuteSearch = {
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    },
                    categories = foodCategories,
                    selectedCategory = state.selectedCategory,
                    onSelectedCategoryChanged = {
                        onTriggerEvent(RecipeListEvents.OnSelectCategory(it))
                    },
                )
            },
        ) {
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                },
                onClickRecipeListItem = onClickRecipeListItem
            )
        }
    }

}