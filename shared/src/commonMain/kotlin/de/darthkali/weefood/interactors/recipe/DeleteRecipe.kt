package de.darthkali.weefood.interactors.recipe

import de.darthkali.weefood.datasource.database.repository.recipe.RecipeRepository
import de.darthkali.weefood.datasource.database.repository.recipeIngredient.RecipeIngredientRepository
import de.darthkali.weefood.util.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteRecipe : KoinComponent {

//    1. delete all recipeIngredients with the recipeId which will be deleted+
//    2. delete all weekRecipes with the recipeId which will be deleted
//    3. delete recipe with recipeId

    private val recipeIngredientRepository: RecipeIngredientRepository by inject()
    private val recipeRepository: RecipeRepository by inject()
    private val logger = Logger("DeleteRecipeIngredient")

    /**
     * @param recipeId: Int
     *
     * delete all corresponding recipeIngredients for the recipe in the database
     * delete the recipe in the database
     *
     * @return boolean
     */
    fun execute(recipeId: Int): Boolean {
        return try {
            return (recipeIngredientRepository.deleteAllRecipeIngredientsByRecipeId(recipeId) && recipeRepository.deleteRecipeById(recipeId))
        } catch (e: Exception) {
            logger.log(e.toString())
            false
        }
    }
}