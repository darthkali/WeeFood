package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import de.darthkali.weefood.android.presentation.screens.BaseViewModel
import de.darthkali.weefood.datasource.database.model.RecipeDb
import de.darthkali.weefood.presentation.new_recipe.NewRecipeEvents
import de.darthkali.weefood.presentation.new_recipe.NewRecipeState
import de.darthkali.weefood.util.Logger


class NewRecipeViewModel: BaseViewModel() {

    private val logger = Logger("NewRecipeViewModel")

    val state: MutableState<NewRecipeState> = mutableStateOf(NewRecipeState())

    fun onTriggerEvent(event: NewRecipeEvents){
        when (event){
            is NewRecipeEvents.OnUpdateName -> {
                onUpdateRecipe(state.value.recipeDb.copy(name = event.name))
            }
            is NewRecipeEvents.OnUpdateImage -> {
                onUpdateRecipe(state.value.recipeDb.copy(image = event.image))
            }
            is NewRecipeEvents.OnUpdateCookingTime -> {
                onUpdateRecipe(state.value.recipeDb.copy(cooking_time = event.cooking_time))
            }
            is NewRecipeEvents.OnUpdateCookingTimeUnit -> {
                onUpdateRecipe(state.value.recipeDb.copy(cooking_time_unit = event.cooking_time_unit))
            }
            is NewRecipeEvents.OnUpdateDescription -> {
                onUpdateRecipe(state.value.recipeDb.copy(description = event.description))
            }
            else -> {
                logger.log("Something went wrong.")
            }
        }
    }

    private fun onUpdateRecipe(recipeDb: RecipeDb){
        state.value = state.value.copy(recipeDb = recipeDb)
    }

}