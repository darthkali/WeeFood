package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.darthkali.weefood.domain.model.Ingredient
import de.darthkali.weefood.interactors.ingredient_list.GetAllIngredients
import de.darthkali.weefood.interactors.ingredient_list.SaveIngredient
import de.darthkali.weefood.presentation.ingredient_list.IngredientListEvents
import de.darthkali.weefood.interactors.ingredient_list.SearchIngredient
import de.darthkali.weefood.presentation.ingredient_list.IngredientListState
import de.darthkali.weefood.presentation.new_recipe.NewRecipeEvents
import de.darthkali.weefood.presentation.new_recipe.NewRecipeState
import de.darthkali.weefood.util.Logger
import kotlin.collections.ArrayList
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class NewRecipeViewModel: ViewModel(), KoinComponent {

    private val logger = Logger("NewRecipeViewModel")

    val state: MutableState<NewRecipeState> = mutableStateOf(NewRecipeState())

    fun onTriggerEvent(event: NewRecipeEvents){
        when (event){
            is NewRecipeEvents.OnUpdateName -> {
                state.value = state.value.copy(name = event.name)
            }
            is NewRecipeEvents.OnUpdateImage -> {
                state.value = state.value.copy(image = event.image)
            }
            is NewRecipeEvents.OnUpdateCookingTime -> {
                state.value = state.value.copy(cooking_time = event.cooking_time)
            }
            is NewRecipeEvents.OnUpdateCookingTimeUnit -> {
                state.value = state.value.copy(cooking_time_unit = event.cooking_time_unit)
            }
            is NewRecipeEvents.OnUpdateDescription -> {
                state.value = state.value.copy(description = event.description)
            }
            else -> {
                logger.log("Something went wrong.")
            }
        }
    }
}