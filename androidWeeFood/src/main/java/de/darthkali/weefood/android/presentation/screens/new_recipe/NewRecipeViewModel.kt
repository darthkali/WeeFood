package de.darthkali.weefood.android.presentation.screens.new_recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import de.darthkali.weefood.android.presentation.screens.BaseViewModel
import de.darthkali.weefood.domain.model.Recipe
import de.darthkali.weefood.interactors.recipe.GetRecipe
import de.darthkali.weefood.interactors.recipe.SaveRecipe
import de.darthkali.weefood.interactors.recipe_ingredient.DeleteRecipeIngredient
import de.darthkali.weefood.interactors.recipe_ingredient.GetIngredientsFromRecipe
import de.darthkali.weefood.presentation.new_recipe.NewRecipeEvents
import de.darthkali.weefood.presentation.new_recipe.NewRecipeState
import de.darthkali.weefood.util.Logger
import org.koin.core.component.inject

class NewRecipeViewModel(
    recipeId: Int,
) : BaseViewModel() {

    private val logger = Logger("NewRecipeViewModel")
    private val saveRecipe: SaveRecipe by inject()
    private val getRecipe: GetRecipe by inject()
    private val deleteRecipeIngredient: DeleteRecipeIngredient by inject()
    private val getIngredientsFromRecipe: GetIngredientsFromRecipe by inject()

    val state: MutableState<NewRecipeState> = mutableStateOf(NewRecipeState())

    init {
        if (recipeId != 0) { //TODO: vlt nicht nötig, da wir im getRecipe schon auf > 0 prüfen
            onTriggerEvent(NewRecipeEvents.GetRecipe(recipeId = recipeId))
        }
    }


    fun onTriggerEvent(event: NewRecipeEvents) {
        when (event) {
            is NewRecipeEvents.GetRecipe -> {

                val recipe: Recipe = getRecipe.execute(recipeId = event.recipeId)!!
                state.value = state.value.copy(recipe = recipe)

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
                onUpdateRecipe(state.value.recipe.copy(recipeDescription = event.description))
            }
            is NewRecipeEvents.OnAddIngredient -> {
                onUpdateRecipe(state.value.recipe.copy(databaseId = saveRecipe.execute(state.value.recipe)))
            }
            is NewRecipeEvents.OnDeleteIngredient -> {
                if (deleteRecipeIngredient.execute(
                        state.value.recipe.databaseId!!,
                        event.ingredient.internalId!!
                    )
                ) {
                    val ingredients =
                        getIngredientsFromRecipe.execute(state.value.recipe.databaseId!!)
                    onUpdateRecipe(state.value.recipe.copy(ingredients = ingredients!!))
                }
            }
            is NewRecipeEvents.OnSaveRecipe -> {
                saveRecipe.execute(state.value.recipe)
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