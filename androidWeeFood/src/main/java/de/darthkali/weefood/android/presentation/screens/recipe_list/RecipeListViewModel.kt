package de.darthkali.weefood.android.presentation.screens.recipe_list

import androidx.lifecycle.viewModelScope
import de.darthkali.weefood.android.presentation.screens.BaseViewModel
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.interactors.recipe.SearchRecipes
import de.darthkali.weefood.presentation.recipe_list.RecipeListEvents
import de.darthkali.weefood.presentation.recipe_list.RecipeListState
import de.darthkali.weefood.util.Logger
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class RecipeListViewModel(
    query: String = ""
) : BaseViewModel<RecipeListEvents, RecipeListState>() {

    private val searchRecipes: SearchRecipes by inject()
    private val logger = Logger("RecipeListViewModel")


//    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        viewModelScope.launch {
            setState {
                copy(query = query)
            }
            loadRecipes()
        }
    }

    override fun setInitialState() =
        RecipeListState(
            isLoading = true,
            page = 1,
            query = "",
            recipes = listOf(),
        )


    override fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            RecipeListEvents.LoadRecipe -> {
                loadRecipes()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                setState {
                    copy(query = event.query)
                }
//                state.value = state.value.copy(query = event.query)
            }
            else -> {
                logger.log("Something went wrong.")
            }
        }
    }


    /**
     * Get the next page of recipes
     */
    private fun nextPage() {
        setState {
            copy(page = viewState.value.page + 1)
        }
//        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch() {
        setState {
            copy(page = 1, recipes = listOf())
        }
//        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            query = viewState.value.query,
            page = viewState.value.page,
        ).collectCommon(viewModelScope) { dataState ->

            setState {
                copy(isLoading = dataState.isLoading)
            }
//            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }
        }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(viewState.value.recipes)
        curr.addAll(recipes)

        setState {
            copy(recipes = curr)
        }
//        state.value = state.value.copy(recipes = curr)
    }




}