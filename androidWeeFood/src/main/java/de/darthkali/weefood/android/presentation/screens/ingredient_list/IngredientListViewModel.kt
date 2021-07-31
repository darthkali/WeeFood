package de.darthkali.weefood.android.presentation.screens.ingredient_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.interactors.recipe_list.GetAllIngredients
import de.darthkali.weefood.interactors.recipe_list.SaveIngredient
import de.darthkali.weefood.presentation.ingredient_list.IngredientListEvents
import de.darthkali.weefood.interactors.recipe_list.SearchIngredient
import de.darthkali.weefood.presentation.ingredient_list.IngredientListState
import de.darthkali.weefood.util.Logger
import javax.inject.Inject
import kotlin.collections.ArrayList
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class IngredientListViewModel: ViewModel(), KoinComponent {

    private val searchIngredient: SearchIngredient  by inject()
    private val saveIngredient: SaveIngredient by inject()
    private val getAllIngredients: GetAllIngredients by inject()


    private val logger = Logger("IngredientListViewModel")

    val state: MutableState<IngredientListState> = mutableStateOf(IngredientListState())

    init {
        loadIngredients()
    }

    fun onTriggerEvent(event: IngredientListEvents){
        when (event){
            IngredientListEvents.LoadIngredient -> {
                loadIngredients()
            }
            IngredientListEvents.NewSearch -> {
                newSearch()
            }
            IngredientListEvents.NextPage -> {
                nextPage()
            }
            is IngredientListEvents.SaveIngredient -> {
                saveIngredient(event.ingredient)
            }
            is IngredientListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query =  event.query)
            }
            else -> {
                logger.log("Something went wrong.")
            }
        }
    }

    private fun saveIngredient(ingredient:Ingredient) {
        saveIngredient.saveIngredient(ingredient)
        for(ingredientItem in getAllIngredients.GetAllIngredients()){
            logger.log(ingredientItem.toString())
        }
    }


    /**
     * Get the next page of recipes
     */
    private fun nextPage(){
        state.value = state.value.copy(page = state.value.page + 1)
        loadIngredients()
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch(){
        state.value = state.value.copy(page = 1, ingredients = listOf())
        loadIngredients()
    }

    private fun loadIngredients(){
        searchIngredient.execute(
            query = state.value.query,
            page = state.value.page,
        ).collectCommon(viewModelScope) { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { ingredients ->
                appendIngredients(ingredients)
            }
        }
    }

    private fun appendIngredients(ingredients: List<Ingredient>){
        val curr = ArrayList(state.value.ingredients)
        curr.addAll(ingredients)
        state.value = state.value.copy(ingredients = curr)
    }
}