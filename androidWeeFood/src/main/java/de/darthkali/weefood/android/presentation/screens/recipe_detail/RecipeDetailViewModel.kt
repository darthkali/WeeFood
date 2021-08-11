package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import de.darthkali.weefood.android.presentation.screens.BaseViewModel
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.interactors.recipe.GetRecipe
import de.darthkali.weefood.interactors.recipe.SaveRecipe
import de.darthkali.weefood.interactors.recipe_ingredient.DeleteRecipeIngredient
import de.darthkali.weefood.interactors.recipe_ingredient.GetIngredientsFromRecipe
import de.darthkali.weefood.presentation.new_recipe.NewRecipeEvents
import de.darthkali.weefood.presentation.new_recipe.NewRecipeState
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailEvents
import de.darthkali.weefood.presentation.recipe_detail.RecipeDetailState
import de.darthkali.weefood.util.Logger
import org.koin.core.component.inject

class RecipeDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val logger = Logger("RecipeDetailViewModel")
    private val getRecipe: GetRecipe by inject()

    private var _state = mutableStateOf(RecipeDetailState())
        private set

    val state: MutableState<RecipeDetailState>
        get() = _state



    init {
        savedStateHandle.get<String>("recipeId")?.let { id ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId = id.toInt()))
        }
    }


    fun onTriggerEvent(event: RecipeDetailEvents) {
        when (event) {
            is RecipeDetailEvents.GetRecipe -> {
                val recipe: Recipe = getRecipe.execute(recipeId = event.recipeId)!!
                state.value = state.value.copy(recipe = recipe)
            }
            else -> {
                logger.log("Something went wrong.")
            }
        }
    }

    private fun onUpdateRecipe(recipe: Recipe) {
        state.value = state.value.copy(recipe = recipe, changed = state.value.changed + 1)
    }

}