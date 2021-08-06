package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import de.darthkali.weefood.android.presentation.screens.BaseViewModel
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.interactors.new_recipe.GetRecipe
import de.darthkali.weefood.interactors.new_recipe.SaveRecipe
import de.darthkali.weefood.mockFactory.IngredientMock
import de.darthkali.weefood.presentation.new_recipe.NewRecipeEvents
import de.darthkali.weefood.presentation.new_recipe.NewRecipeState
import de.darthkali.weefood.util.Logger
import org.koin.core.component.inject


class NewRecipeViewModel() : BaseViewModel() {

    private val logger = Logger("NewRecipeViewModel")
    private val saveRecipe: SaveRecipe by inject()
    private val getRecipe: GetRecipe by inject()

    val state: MutableState<NewRecipeState> = mutableStateOf(NewRecipeState())

    init {
        state.value.recipe.internalId?.let {

            NewRecipeEvents.GetRecipe(recipeId = it) //TODO
            NewRecipeEvents.OnUpdateName(state.value.recipe.name)

        }
//            onTriggerEvent(NewRecipeEvents.GetRecipe(recipeId = state.value.recipe.internalId!!))
    }


    fun onTriggerEvent(event: NewRecipeEvents) {
        when (event) {
            is NewRecipeEvents.GetRecipe -> {
                state.value =
                    state.value.copy(recipe = getRecipe.execute(recipeId = event.recipeId)!!)
            }
            is NewRecipeEvents.OnUpdateName -> {
                onUpdateRecipe(state.value.recipe.copy(name = event.name))
            }
            is NewRecipeEvents.OnUpdateImage -> {
                onUpdateRecipe(state.value.recipe.copy(image = event.image))
            }
            is NewRecipeEvents.OnUpdateCookingTime -> {
                onUpdateRecipe(state.value.recipe.copy(cooking_time = event.cooking_time))
            }
            is NewRecipeEvents.OnUpdateCookingTimeUnit -> {
                onUpdateRecipe(state.value.recipe.copy(cooking_time_unit = event.cooking_time_unit))
            }
            is NewRecipeEvents.OnUpdateDescription -> {
                onUpdateRecipe(state.value.recipe.copy(description = event.description))
            }
            is NewRecipeEvents.OnSaveRecipe -> {
//                state.value.recipe.ingredients = IngredientMock.ingredientList // TODO: nur zum testen
//                state.value.recipe.internalId = 23
                saveRecipe.execute(state.value.recipe)
            }
            else -> {
                logger.log("Something went wrong.")
            }
        }
    }

    private fun onUpdateRecipe(recipe: Recipe) {
        state.value = state.value.copy(recipe = recipe)
    }

}